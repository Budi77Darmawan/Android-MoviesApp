package com.bd_drmwan.core.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CastModel(
    val id: Int?,
    val name: String?,
    val gender: Int?,
    val adult: String?,
    val popularity: String?,
    val imageUri: String?
) : Parcelable