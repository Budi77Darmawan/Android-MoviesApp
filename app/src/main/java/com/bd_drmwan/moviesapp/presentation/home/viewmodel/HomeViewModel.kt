package com.bd_drmwan.moviesapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.presentation.home.usecase.IHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: IHomeUseCase
) : ViewModel() {

    private val _nowPlayingMovies = MutableStateFlow<Resource<List<MovieModel>>?>(null)
    private val _upComingMovies = MutableStateFlow<Resource<List<MovieModel>>?>(null)
    private val _topRatedMovies = MutableStateFlow<Resource<List<MovieModel>>?>(null)
    private val _popularMovies = MutableStateFlow<Resource<List<MovieModel>>?>(null)
    private val _popularActors = MutableStateFlow<Resource<List<CastModel>>?>(null)

    val nowPlayingMovies get() = _nowPlayingMovies.asStateFlow()
    val upComingMovies get() = _upComingMovies.asStateFlow()
    val topRatedMovies get() = _topRatedMovies.asStateFlow()
    val popularMovies get() = _popularMovies.asStateFlow()
    val popularActors get() = _popularActors.asStateFlow()

    init {
        getUpComingMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getPopularMovies()
        getPopularActors()
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            useCase.getNowPlaying().collect {
                    _nowPlayingMovies.emit(it)
                }
        }
    }

    private fun getUpComingMovies() {
        viewModelScope.launch {
            useCase.getUpComingMovies().collect {
                _upComingMovies.emit(it)
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            useCase.getTopRatedMovies().collect {
                _topRatedMovies.emit(it)
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            useCase.getPopularMovies().collect {
                _popularMovies.emit(it)
            }
        }
    }

    private fun getPopularActors() {
        viewModelScope.launch {
            useCase.getPopularActors().collect {
                _popularActors.emit(it)
            }
        }
    }
}