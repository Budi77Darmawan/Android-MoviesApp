package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun getMovies(moviesType: MoviesType): Flow<Resource<List<MovieModel>>>
}