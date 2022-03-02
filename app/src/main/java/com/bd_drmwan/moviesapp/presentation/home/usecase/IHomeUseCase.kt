package com.bd_drmwan.moviesapp.presentation.home.usecase

import com.bd_drmwan.core.main.domain.model.ActorModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IHomeUseCase {
    suspend fun getUpComingMovies(): Flow<Resource<List<MovieModel>>>
    suspend fun getNowPlaying(): Flow<Resource<List<MovieModel>>>
    suspend fun getTopRatedMovies(): Flow<Resource<List<MovieModel>>>

    suspend fun getPopularActors(): Flow<Resource<List<ActorModel>>>
}