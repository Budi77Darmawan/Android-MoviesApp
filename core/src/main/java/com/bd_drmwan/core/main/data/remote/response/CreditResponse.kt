package com.bd_drmwan.core.main.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("cast")
    val cast: List<CastResult>?
)