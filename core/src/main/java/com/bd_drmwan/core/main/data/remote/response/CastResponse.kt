package com.bd_drmwan.core.main.data.remote.response

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("results")
    val results: List<CastResult>?
)

data class CastResult(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("adult")
    val adult: String?,
    @SerializedName("known_for_department")
    val known_for_department: String?,
    @SerializedName("popularity")
    val popularity: String?,
    @SerializedName("known_for")
    val listMovies: List<MovieResult>?,
    @SerializedName("profile_path")
    val image: String?,
    @SerializedName("character")
    val characterName: String?
)