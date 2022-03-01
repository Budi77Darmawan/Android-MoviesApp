package com.bd_drmwan.core.utils

import com.bd_drmwan.core.main.data.remote.response.MoviesResponse
import com.bd_drmwan.core.main.domain.model.MovieModel

object DataMapper {
    fun mapMoviesResponseToMoviesModel(data: MoviesResponse?): List<MovieModel> {
        return data?.let { mov ->
            mov.results?.map {
                MovieModel(
                    it.id,
                    it.title,
                    it.overview,
                    it.releaseDate,
                    it.poster,
                    it.backdrop,
                    it.vote
                )
            } ?: listOf()
        } ?: run { listOf() }
    }
}