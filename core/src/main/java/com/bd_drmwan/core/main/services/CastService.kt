package com.bd_drmwan.core.main.services

import com.bd_drmwan.core.main.data.remote.response.CastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CastService {
    @GET("person/popular")
    suspend fun getPopularCast(
        @Query("api_key") api_key: String?,
        @Query("language") language: String?,
        @Query("page") page: Int? = 1
    ): Response<CastResponse>
}