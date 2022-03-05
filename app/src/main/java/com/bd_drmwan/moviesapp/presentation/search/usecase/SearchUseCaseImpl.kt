package com.bd_drmwan.moviesapp.presentation.search.usecase

import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    private val moviesRepository: IMoviesRepository
): ISearchUseCase {

    override suspend fun searchMovies(title: String): Flow<Resource<List<MovieModel>>> {
        return moviesRepository.searchMovies(title)
    }
}