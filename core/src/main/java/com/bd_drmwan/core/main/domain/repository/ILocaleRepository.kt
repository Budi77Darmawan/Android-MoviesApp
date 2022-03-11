package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface ILocaleRepository {
    fun addToFavorite(movie: MovieModel)
    fun deleteFromFavorite(movie: MovieModel)
    suspend fun getMovies(movieId: Int): Flow<Resource<MovieModel?>>
    suspend fun getFavoriteMovies(): Flow<Resource<List<MovieModel>>>
}