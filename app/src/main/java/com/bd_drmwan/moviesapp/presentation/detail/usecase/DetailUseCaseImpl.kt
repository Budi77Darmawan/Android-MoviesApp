package com.bd_drmwan.moviesapp.presentation.detail.usecase

import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.domain.repository.ILocaleRepository
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCaseImpl @Inject constructor(
    private val moviesRepository: IMoviesRepository,
    private val localeRepository: ILocaleRepository
): IDetailUseCase {

    override suspend fun getLocaleMovie(movieId: Int): Flow<Resource<MovieModel?>> {
        return localeRepository.getMovies(movieId)
    }

    override fun addToFavorite(movie: MovieModel) {
        localeRepository.addToFavorite(movie)
    }

    override fun deleteFromFavorite(movie: MovieModel) {
        localeRepository.deleteFromFavorite(movie)
    }

    override suspend fun getCastOnMovie(movieId: Int): Flow<Resource<List<CastModel>>> {
        return moviesRepository.getCastOnMovie(movieId)
    }
}