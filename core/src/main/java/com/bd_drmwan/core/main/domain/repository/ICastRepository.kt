package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface ICastRepository {
    suspend fun getPopularActors(): Flow<Resource<List<CastModel>>>
}