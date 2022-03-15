package com.bd_drmwan.core.main.data.remote.response

import com.bd_drmwan.core.main.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)