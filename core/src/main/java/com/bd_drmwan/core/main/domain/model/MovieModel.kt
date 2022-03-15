package com.bd_drmwan.core.main.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
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
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
): Parcelable

