package com.bd_drmwan.moviesapp.modules

import com.bd_drmwan.core.main.domain.repository.IActorsRepository
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.moviesapp.presentation.home.usecase.HomeUseCaseImpl
import com.bd_drmwan.moviesapp.presentation.home.usecase.IHomeUseCase
import com.bd_drmwan.moviesapp.presentation.search.usecase.ISearchUseCase
import com.bd_drmwan.moviesapp.presentation.search.usecase.SearchUseCaseImpl
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

    @Provides
    fun provideSearchUseCase(
        moviesRepository: IMoviesRepository
    ): ISearchUseCase {
        return SearchUseCaseImpl(moviesRepository)
    }
}