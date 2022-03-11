package com.bd_drmwan.moviesapp.modules

import com.bd_drmwan.core.main.domain.repository.ICastRepository
import com.bd_drmwan.core.main.domain.repository.ILocaleRepository
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.moviesapp.presentation.detail.usecase.DetailUseCaseImpl
import com.bd_drmwan.moviesapp.presentation.detail.usecase.IDetailUseCase
import com.bd_drmwan.moviesapp.presentation.home.usecase.HomeUseCaseImpl
import com.bd_drmwan.moviesapp.presentation.home.usecase.IHomeUseCase
import com.bd_drmwan.moviesapp.presentation.search.usecase.ISearchUseCase
import com.bd_drmwan.moviesapp.presentation.search.usecase.SearchUseCaseImpl
import com.bd_drmwan.moviesapp.presentation.splash.usecase.ISplashUseCase
import com.bd_drmwan.moviesapp.presentation.splash.usecase.SplashUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideSplashUseCase(
        moviesRepository: IMoviesRepository
    ): ISplashUseCase {
        return SplashUseCaseImpl(moviesRepository)
    }

    @Provides
    fun provideHomeUseCase(
        moviesRepository: IMoviesRepository,
        castRepository: ICastRepository
    ): IHomeUseCase {
        return HomeUseCaseImpl(moviesRepository, castRepository)
    }

    @Provides
    fun provideSearchUseCase(
        moviesRepository: IMoviesRepository
    ): ISearchUseCase {
        return SearchUseCaseImpl(moviesRepository)
    }

    @Provides
    fun provideDetailUseCase(
        moviesRepository: IMoviesRepository,
        localeRepository: ILocaleRepository
    ): IDetailUseCase {
        return DetailUseCaseImpl(moviesRepository, localeRepository)
    }
}