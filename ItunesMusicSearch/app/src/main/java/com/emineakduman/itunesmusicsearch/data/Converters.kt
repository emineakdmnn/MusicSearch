package com.emineakduman.itunesmusicsearch.data

import androidx.room.TypeConverter
import com.squareup.moshi.*
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

class DateJsonConverter : JsonAdapter<Date>() {

    private val jsonDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    @FromJson
    override fun fromJson(reader: JsonReader): Date? =
        try {
            val dateAsString = reader.nextString()
            synchronized(jsonDateFormat) {
                jsonDateFormat.parse(dateAsString)
            }
        } catch (e: Exception) {
            null
        }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        value?.let {
            writer.value(jsonDateFormat.format(it))
        }
    }
}

class DateRoomConverter {

    private val roomDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    @TypeConverter
    fun dateToString(date: Date): String = roomDateFormat.format(date)

    @TypeConverter
    fun stringToDate(string: String): Date? = roomDateFormat.parse(string)
}