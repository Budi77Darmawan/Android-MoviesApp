package com.bd_drmwan.moviesapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IMoviesRepository
) : ViewModel() {

    private val _listMovies = MutableSharedFlow<Resource<List<MovieModel>>>()
    val listMovies get() = _listMovies.asSharedFlow()

    fun getPopularMovie() {
        viewModelScope.launch {
            repository.getMovies(MoviesType.POPULAR).collect {
                    _listMovies.emit(it)
                }
        }
    }
}