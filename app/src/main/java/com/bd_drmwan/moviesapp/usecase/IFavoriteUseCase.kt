package com.bd_drmwan.moviesapp.usecase

import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IFavoriteUseCase {
    suspend fun getFavoriteMovies(): Flow<Resource<List<MovieModel>>>
}