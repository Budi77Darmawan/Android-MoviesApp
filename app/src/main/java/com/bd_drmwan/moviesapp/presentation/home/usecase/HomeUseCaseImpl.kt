package com.bd_drmwan.moviesapp.presentation.home.usecase

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.domain.model.ActorModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.domain.repository.IActorsRepository
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val actorsRepository: IActorsRepository
): IHomeUseCase {
    override suspend fun getUpComingMovies(): Flow<Resource<List<MovieModel>>> {
        return moviesRepository.getMovies(MoviesType.UPCOMING)
    }

    override suspend fun getNowPlaying(): Flow<Resource<List<MovieModel>>> {
        return moviesRepository.getMovies(MoviesType.NOW_PLAYING)
    }

    override suspend fun getTopRatedMovies(): Flow<Resource<List<MovieModel>>> {
        return moviesRepository.getMovies(MoviesType.TOP_RATED)
    }

    override suspend fun getPopularActors(): Flow<Resource<List<ActorModel>>> {
        return actorsRepository.getPopularActors()
    }
}