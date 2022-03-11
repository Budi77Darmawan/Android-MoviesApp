package com.bd_drmwan.moviesapp.presentation.detail.usecase

import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IDetailUseCase {
    suspend fun getCastOnMovie(movieId: Int): Flow<Resource<List<CastModel>>>
    suspend fun getLocaleMovie(movieId: Int): Flow<Resource<MovieModel?>>
    fun addToFavorite(movie: MovieModel)
    fun deleteFromFavorite(movie: MovieModel)
}