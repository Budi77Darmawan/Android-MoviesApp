package com.bd_drmwan.moviesapp.modules

import com.bd_drmwan.moviesapp.usecase.FavoriteUseCaseImpl
import com.bd_drmwan.moviesapp.usecase.IFavoriteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideFavoriteUseCase(favoriteUseCaseImpl: FavoriteUseCaseImpl): IFavoriteUseCase
}