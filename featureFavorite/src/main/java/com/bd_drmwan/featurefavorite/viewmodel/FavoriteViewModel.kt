package com.bd_drmwan.featurefavorite.viewmodel

import androidx.lifecycle.ViewModel
import com.bd_drmwan.moviesapp.usecase.IFavoriteUseCase

class FavoriteViewModel(
    private val useCase: IFavoriteUseCase
) : ViewModel() {

    suspend fun getFavoriteMovies() = useCase.getFavoriteMovies()
}