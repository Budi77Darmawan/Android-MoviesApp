package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.main.data.locale.source.LocaleDataSource
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocaleRepositoryImpl @Inject constructor(
    private val localeDataSource: LocaleDataSource
): ILocaleRepository {
    override fun addToFavorite(movie: MovieModel) {
        val entity = DataMapper.mapModelToEntity(movie)
        localeDataSource.addFromFavorite(entity)
    }

    override fun deleteFromFavorite(movie: MovieModel) {
        val entity = DataMapper.mapModelToEntity(movie)
        localeDataSource.deleteFromFavorite(entity)
    }

    override suspend fun getFavoriteMovies(): Flow<Resource<List<MovieModel>>> {
        return flow {
            emit(Resource.Loading())
            localeDataSource.getFavoriteMovies().collect {
                it?.let { data ->
                    val model = data.map { movie -> DataMapper.mapEntityToModel(movie) }
                    emit(Resource.Success(model.filterNotNull()))
                } ?: run {
                    emit(Resource.Success(listOf<MovieModel>()))
                }
            }
        }
    }

    override suspend fun getMovies(movieId: Int): Flow<Resource<MovieModel?>> {
        return flow {
            emit(Resource.Loading())
            localeDataSource.getMovie(movieId).collect {
                val model = DataMapper.mapEntityToModel(it)
                emit(Resource.Success<MovieModel?>(model))
            }
        }
    }
}