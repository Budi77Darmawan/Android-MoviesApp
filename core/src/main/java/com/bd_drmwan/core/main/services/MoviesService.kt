package com.bd_drmwan.core.main.services

import com.bd_drmwan.core.main.data.remote.response.CreditResponse
import com.bd_drmwan.core.main.data.remote.response.GenresResponse
import com.bd_drmwan.core.main.data.remote.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int? = 1
    ): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int? = 1
    ): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int? = 1
    ): Response<MoviesResponse>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String?,
        @Query("query") title: String?,
        @Query("language") language: String?,
        @Query("page") page: Int? = 1
    ): Response<MoviesResponse>

    @GET("movie/{id}/credits")
    suspend fun getCreditMovie(
        @Path("id") movieId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ): Response<CreditResponse>

    @GET("genre/movie/list")
    suspend fun getGenreMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ): Response<GenresResponse>

}