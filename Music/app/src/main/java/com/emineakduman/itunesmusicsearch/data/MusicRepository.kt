package com.emineakduman.itunesmusicsearch.data

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emineakduman.itunesmusicsearch.network.MusicService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val TIMESTAMP_KEY = "timestamp"

@Singleton
class MusicRepository @Inject internal constructor(
    private val musicService: MusicService,
    private val appDatabase: AppDatabase,
    private val sharedPreferences: SharedPreferences
) {
    private val searchResults: MutableList<Track> = mutableListOf()
    private val _searchResults: MutableLiveData<List<Track>> = MutableLiveData()
    private val _timestamp: MutableLiveData<String> = MutableLiveData()

    fun getSearchResults(): LiveData<List<Track>> = _searchResults

    fun getTimestamp(): LiveData<String> = _timestamp

    suspend fun loadTracksFromDatabase() =
        repopulateSearchResults(appDatabase.trackDao().getTracks())

    fun loadTimestampFromSharedPref() {
        _timestamp.value = sharedPreferences.getString(TIMESTAMP_KEY, "")
    }

    fun searchMusic(keywords: String) =
        GlobalScope.launch(Dispatchers.Main) {
            val request = musicService.getSearchResultsAsync(keywords)
            try {
                val response = request.await()
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        repopulateSearchResults(it)
                        appDatabase.trackDao().insertAll(it)
                        saveTimestampToSharedPref()
                    }
                }
            } catch (e: Exception) {
                Log.d("MusicRepository ", e.message ?: "")
            }
        }

    private fun repopulateSearchResults(source: List<Track>) {
        searchResults.clear()
        searchResults.addAll(source)
        _searchResults.value = searchResults
    }

    private fun saveTimestampToSharedPref() {
        val dateFormat = SimpleDateFormat("EEE MMM dd h:mma", Locale.getDefault())
        val timestamp = dateFormat.format(Calendar.getInstance().time)
        sharedPreferences.edit().putString(TIMESTAMP_KEY, timestamp).apply()
        _timestamp.value = timestamp
    }
}