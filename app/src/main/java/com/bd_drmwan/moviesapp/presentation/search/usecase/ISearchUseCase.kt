package com.bd_drmwan.moviesapp.presentation.search.usecase

import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface ISearchUseCase {
    suspend fun searchMovies(title: String): Flow<Resource<List<MovieModel>>>
}