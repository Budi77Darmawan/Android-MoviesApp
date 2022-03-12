package com.bd_drmwan.moviesapp.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.presentation.search.usecase.ISearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: ISearchUseCase
) : ViewModel() {

    private val _moviesByQuery = MutableStateFlow<Resource<List<MovieModel>>?>(null)
    val moviesByQuery get() = _moviesByQuery.asStateFlow()

    fun searchMovies(title: String) {
        viewModelScope.launch {
            useCase.searchMovies(title).collect {
                _moviesByQuery.emit(it)
            }
        }
    }
}