package com.bd_drmwan.core.main.data.remote.response

import com.google.gson.annotations.SerializedName

data class ActorsResponse(
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    val results: List<ActorResult>?
)

data class ActorResult(
    val id: Int?,
    val name: String?,
    val gender: Int?,
    val adult: String?,
    val known_for_department: String?,
    val popularity: String?,
    @SerializedName("known_for")
    val listMovies: List<MovieResult>?,
    @SerializedName("profile_path")
    val image: String?
)