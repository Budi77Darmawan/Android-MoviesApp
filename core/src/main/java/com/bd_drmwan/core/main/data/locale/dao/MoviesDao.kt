package com.bd_drmwan.core.main.data.locale.dao

import androidx.room.*
import com.bd_drmwan.core.main.data.locale.entity.CastEntity
import com.bd_drmwan.core.main.data.locale.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavorite(movie: MoviesEntity)

    @Delete
    fun deleteFromFavorite(movie: MoviesEntity)

    @Query("SELECT * from moviesEntity ORDER BY title ASC")
    fun getAllMovies(): Flow<List<MoviesEntity>?>

    @Query("SELECT * from moviesEntity WHERE id = :idMovie")
    fun getMovie(idMovie: Int): Flow<MoviesEntity?>

    @Query("SELECT * from castEntity WHERE idMovie = :idMovie")
    fun getCastOnMovie(idMovie: Int): Flow<List<CastEntity>?>
}