package com.bd_drmwan.core.utils

import com.bd_drmwan.core.BuildConfig.BASE_IMAGE_URL
import com.bd_drmwan.core.Genres
import com.bd_drmwan.core.main.data.locale.entity.GenreEntity
import com.bd_drmwan.core.main.data.locale.entity.MoviesEntity
import com.bd_drmwan.core.main.data.remote.response.CastResult
import com.bd_drmwan.core.main.data.remote.response.MoviesResponse
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.model.Genre
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
                    it.voteAverage,
                    it.voteCount,
                    mapGenresIdsToGenre(it.genreIds)
                )
            } ?: listOf()
        } ?: run { listOf() }
    }

    fun mapCastResponseToCastModel(data: List<CastResult>?): List<CastModel> {
        return data?.map {
            CastModel(
                it.id,
                it.name,
                it.gender,
                it.adult,
                it.popularity,
                it.characterName,
                BASE_IMAGE_URL + it.image
            )
        } ?: listOf()
    }

    private fun mapGenresIdsToGenre(data: List<Int>?): List<Genre?>? {
        val genres = Genres.getData()
        return data?.let {
            it.map { genreId ->
                genres.find { gen -> gen.id == genreId }
            }
        } ?: run { null }
    }

    fun mapModelToEntity(data: MovieModel): MoviesEntity {
        return MoviesEntity(
            data.id,
            data.title,
            data.overview,
            data.releaseDate,
            data.posterUri,
            data.backdropUri,
            data.voteAverage,
            data.voteCount,
            data.genre?.map { gen -> GenreEntity(gen?.id, gen?.name) }
        )
    }

    fun mapEntityToModel(data: MoviesEntity?): MovieModel? {
        return data?.let { movie ->
            MovieModel(
                movie.id,
                movie.title,
                movie.overview,
                movie.releaseDate,
                movie.posterUri,
                movie.backdropUri,
                movie.voteAverage,
                movie.voteCount,
                movie.genre?.map { gen -> Genre(gen.id, gen.name) }
            )
        } ?: run { null }
    }

}