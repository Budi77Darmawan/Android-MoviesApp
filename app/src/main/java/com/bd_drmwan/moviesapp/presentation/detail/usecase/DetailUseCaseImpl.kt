package com.bd_drmwan.moviesapp.presentation.detail.usecase

import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailUseCaseImpl @Inject constructor(
    private val moviesRepository: IMoviesRepository
): IDetailUseCase {

    override suspend fun getCastOnMovie(movieId: Int): Flow<Resource<List<CastModel>>> {
        return moviesRepository.getCastOnMovie(movieId)
    }
}