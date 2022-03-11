package com.bd_drmwan.core.main.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String?,
    @ColumnInfo(name = "posterUri")
    val posterUri: String?,
    @ColumnInfo(name = "backdropUri")
    val backdropUri: String?,
    @ColumnInfo(name = "voteAverage")
    val voteAverage: String?,
    @ColumnInfo(name = "voteCount")
    val voteCount: Long?,
    @ColumnInfo(name = "genre")
    val genre: List<GenreEntity>?,
    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean = false
)

data class GenreEntity(
    val id: Int?,
    val name: String?
)

data class ListGenre(
    val value: List<GenreEntity>
)