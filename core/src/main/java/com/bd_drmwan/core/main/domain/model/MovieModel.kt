package com.bd_drmwan.core.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val releaseDate: String?,
    val posterUri: String?,
    val backdropUri: String?,
    val voteAverage: String?,
    val voteCount: Long?,
    val genre: List<Genre?>?
) : Parcelable

@Parcelize
data class Genre(
    val id: Int?,
    val name: String?
): Parcelable

