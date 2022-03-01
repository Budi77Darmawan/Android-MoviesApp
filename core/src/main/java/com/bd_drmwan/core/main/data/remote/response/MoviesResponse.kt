package com.bd_drmwan.core.main.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    val results: List<MovieResult>?
)

data class MovieResult(
    val id: Int?,
    val title: String?,
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val vote: String?,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("backdrop_path")
    val backdrop: String?
)