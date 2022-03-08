package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.main.data.remote.source.CastRemoteDataSource
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CastRepositoryImpl @Inject constructor(
    private val remoteDataSource: CastRemoteDataSource
): ICastRepository {
    override suspend fun getPopularActors(): Flow<Resource<List<CastModel>>> {
        return flow {
            remoteDataSource.getPopularActors().collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading())
                    is Resource.Error -> emit(Resource.Error(it.message, it.errorType))
                    is Resource.Success -> {
                        val data = DataMapper.mapCastResponseToCastModel(it.data?.results)
                        emit(Resource.Success(data))
                    }
                }
            }
        }
    }
}