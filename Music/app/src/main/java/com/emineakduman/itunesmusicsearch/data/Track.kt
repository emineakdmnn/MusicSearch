package com.emineakduman.itunesmusicsearch.data

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Keep
@Parcelize
@Entity(tableName = "tracks")
data class Track(
    @PrimaryKey val trackId: String,

    @ColumnInfo val trackName: String,
    @ColumnInfo val artistName: String,
    @ColumnInfo val collectionName: String,

    @ColumnInfo val trackPrice: Float,
    @ColumnInfo val trackExplicitness: String,
    @ColumnInfo val trackTimeMillis: Long,

    @ColumnInfo val trackViewUrl: String,
    @ColumnInfo val artistViewUrl: String,
    @ColumnInfo val collectionViewUrl: String,

    @ColumnInfo val artworkUrl100: String,

    @ColumnInfo val currency: String,
    @ColumnInfo val releaseDate: Date,
    @ColumnInfo val primaryGenreName: String
) : Parcelable