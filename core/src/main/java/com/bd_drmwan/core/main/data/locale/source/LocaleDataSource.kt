package com.bd_drmwan.core.main.data.locale.source

import com.bd_drmwan.core.main.data.locale.dao.MoviesDao
import com.bd_drmwan.core.main.data.locale.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors
import javax.inject.Inject

class LocaleDataSource @Inject constructor(
    private val moviesDao: MoviesDao
) {
    private val executorService = Executors.newSingleThreadExecutor()

    fun getFavoriteMovies(): Flow<List<MoviesEntity>?> = moviesDao.getAllMovies()
    fun getMovie(movieId: Int): Flow<MoviesEntity?> = moviesDao.getMovie(movieId)

    fun addFromFavorite(movie: MoviesEntity) {
        movie.isFavorite = true
        executorService.execute {
            moviesDao.addToFavorite(movie)
        }
    }

    fun deleteFromFavorite(movie: MoviesEntity) {
        executorService.execute {
            moviesDao.deleteFromFavorite(movie)
        }
    }

}