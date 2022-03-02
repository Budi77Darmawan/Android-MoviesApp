package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.main.domain.model.ActorModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IActorsRepository {
    suspend fun getPopularActors(): Flow<Resource<List<ActorModel>>>
}