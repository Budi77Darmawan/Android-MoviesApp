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
    val vote: String?,
    var isFavorite: Boolean = false
) : Parcelable

