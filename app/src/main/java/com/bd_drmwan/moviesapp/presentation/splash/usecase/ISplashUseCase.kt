package com.bd_drmwan.moviesapp.presentation.splash.usecase

import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface ISplashUseCase {
    suspend fun getGenresMovies(): Flow<Resource<Any?>>
}