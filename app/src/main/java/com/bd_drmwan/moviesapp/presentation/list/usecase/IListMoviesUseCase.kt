package com.bd_drmwan.moviesapp.presentation.list.usecase

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IListMoviesUseCase {
    suspend fun getMovies(movieType: MoviesType): Flow<Resource<List<MovieModel>>>
}