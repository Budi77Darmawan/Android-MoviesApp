package com.bd_drmwan.moviesapp.modules

import com.bd_drmwan.moviesapp.usecase.IFavoriteUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun favoriteUseCase(): IFavoriteUseCase
}