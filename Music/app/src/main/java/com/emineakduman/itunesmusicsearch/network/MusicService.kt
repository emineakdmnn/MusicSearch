package com.emineakduman.itunesmusicsearch.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.emineakduman.itunesmusicsearch.data.DateJsonConverter
import com.emineakduman.itunesmusicsearch.data.SearchResponse
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MusicService {

    @GET("/search?country=PH&media=music&limit=60")
    fun getSearchResultsAsync(@Query("term") keywords: String): Deferred<Response<SearchResponse>>

    companion object {

        private const val BASE_URL = "https://itunes.apple.com"

        fun init(): MusicService = Retrofit.Builder()
            .client(
                OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(Date::class.java, DateJsonConverter()).build()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MusicService::class.java)
    }
}