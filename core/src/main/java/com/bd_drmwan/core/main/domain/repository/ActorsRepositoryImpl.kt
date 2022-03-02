package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.main.data.remote.source.ActorsRemoteDataSource
import com.bd_drmwan.core.main.domain.model.ActorModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ActorsRepositoryImpl @Inject constructor(
    private val remoteDataSource: ActorsRemoteDataSource
): IActorsRepository {
    override suspend fun getPopularActors(): Flow<Resource<List<ActorModel>>> {
        return flow {
            remoteDataSource.getPopularActors().collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading())
                    is Resource.Error -> emit(Resource.Error(it.message, it.errorType))
                    is Resource.Success -> {
                        val data = DataMapper.mapActorsResponseToActorsModel(it.data)
                        emit(Resource.Success(data))
                    }
                }
            }
        }
    }
}