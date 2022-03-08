package com.bd_drmwan.moviesapp.presentation.splash.usecase

import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(
    private val moviesRepository: IMoviesRepository
): ISplashUseCase {

    override suspend fun getGenresMovies(): Flow<Resource<Any?>> {
        return moviesRepository.getGenresMovie()
    }
}