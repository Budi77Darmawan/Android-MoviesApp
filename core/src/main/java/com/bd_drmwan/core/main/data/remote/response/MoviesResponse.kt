package com.bd_drmwan.core.main.data.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?,
    @SerializedName("results")
    val results: List<MovieResult>?
)

data class MovieResult(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("backdrop_path")
    val backdrop: String?,
    @SerializedName("runtime")
    val runtime: Long,
    @SerializedName("vote_count")
    val voteCount: Long,
    @SerializedName("vote_average")
    val voteAverage: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?
)