package com.bd_drmwan.core.utils

import com.bd_drmwan.core.BuildConfig.BASE_IMAGE_URL
import com.bd_drmwan.core.main.data.remote.response.ActorsResponse
import com.bd_drmwan.core.main.data.remote.response.MoviesResponse
import com.bd_drmwan.core.main.domain.model.ActorModel
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
                    BASE_IMAGE_URL + it.poster,
                    BASE_IMAGE_URL + it.backdrop,
                    it.vote
                )
            } ?: listOf()
        } ?: run { listOf() }
    }

    fun mapActorsResponseToActorsModel(data: ActorsResponse?): List<ActorModel> {
        return data?.let { act ->
            act.results?.map {
                ActorModel(
                    it.id,
                    it.name,
                    it.gender,
                    it.adult,
                    it.popularity,
                    BASE_IMAGE_URL + it.image
                )
            } ?: listOf()
        } ?: run { listOf() }
    }
}