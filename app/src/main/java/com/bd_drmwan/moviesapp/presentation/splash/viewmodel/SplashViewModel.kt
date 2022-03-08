package com.bd_drmwan.moviesapp.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.bd_drmwan.moviesapp.presentation.splash.usecase.ISplashUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: ISplashUseCase
): ViewModel() {

    suspend fun genresMovies() = useCase.getGenresMovies()
}