package com.bd_drmwan.moviesapp.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.bd_drmwan.moviesapp.presentation.detail.usecase.IDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: IDetailUseCase
): ViewModel() {

    suspend fun getCast(movieId: Int) = useCase.getCastOnMovie(movieId)

}