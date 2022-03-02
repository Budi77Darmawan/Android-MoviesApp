package com.bd_drmwan.moviesapp.modules

import com.bd_drmwan.core.main.domain.repository.IActorsRepository
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.moviesapp.presentation.home.usecase.HomeUseCaseImpl
import com.bd_drmwan.moviesapp.presentation.home.usecase.IHomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideHomeUseCase(
        moviesRepository: IMoviesRepository,
        actorsRepository: IActorsRepository
    ): IHomeUseCase {
        return HomeUseCaseImpl(moviesRepository, actorsRepository)
    }
}