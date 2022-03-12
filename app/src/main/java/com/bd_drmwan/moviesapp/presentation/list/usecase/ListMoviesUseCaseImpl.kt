package com.bd_drmwan.moviesapp.presentation.list.usecase

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: IMoviesRepository
): IListMoviesUseCase {
    override suspend fun getMovies(movieType: MoviesType): Flow<Resource<List<MovieModel>>> {
        return moviesRepository.getMovies(movieType)
    }
}