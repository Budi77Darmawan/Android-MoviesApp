package com.bd_drmwan.moviesapp.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.moviesapp.presentation.list.usecase.IListMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(
    private val useCase: IListMoviesUseCase
): ViewModel() {

    suspend fun getMovies(moviesType: MoviesType) = useCase.getMovies(moviesType)
}