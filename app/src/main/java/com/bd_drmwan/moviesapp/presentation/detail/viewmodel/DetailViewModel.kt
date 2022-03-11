package com.bd_drmwan.moviesapp.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.presentation.detail.usecase.IDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: IDetailUseCase
): ViewModel() {

    suspend fun getLocaleMovie(movieId: Int) = useCase.getLocaleMovie(movieId)

    suspend fun getCast(movieId: Int): Flow<Resource<List<CastModel>>> = useCase.getCastOnMovie(movieId)

    fun updateStatusMovie(isFavorite: Boolean, movie: MovieModel) {
        if (isFavorite) deleteToFavorite(movie)
        else addToFavorite(movie)
    }

    private fun addToFavorite(movie: MovieModel) = useCase.addToFavorite(movie)
    private fun deleteToFavorite(movie: MovieModel) = useCase.deleteFromFavorite(movie)

}