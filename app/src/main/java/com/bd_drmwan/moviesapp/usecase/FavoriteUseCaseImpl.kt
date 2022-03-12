package com.bd_drmwan.moviesapp.usecase

import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.domain.repository.ILocaleRepository
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteUseCaseImpl @Inject constructor(
    private val localeRepository: ILocaleRepository
): IFavoriteUseCase {
    override suspend fun getFavoriteMovies(): Flow<Resource<List<MovieModel>>> {
        return localeRepository.getFavoriteMovies()
    }
}