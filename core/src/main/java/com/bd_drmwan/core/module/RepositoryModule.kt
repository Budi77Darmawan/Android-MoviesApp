package com.bd_drmwan.core.module

import com.bd_drmwan.core.main.data.remote.source.ActorsRemoteDataSource
import com.bd_drmwan.core.main.data.remote.source.MoviesRemoteDataSource
import com.bd_drmwan.core.main.domain.repository.ActorsRepositoryImpl
import com.bd_drmwan.core.main.domain.repository.IActorsRepository
import com.bd_drmwan.core.main.domain.repository.IMoviesRepository
import com.bd_drmwan.core.main.domain.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(
        remoteDataSource: MoviesRemoteDataSource
    ): IMoviesRepository {
        return MoviesRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideActorsRepository(
        remoteDataSource: ActorsRemoteDataSource
    ): IActorsRepository {
        return ActorsRepositoryImpl(remoteDataSource)
    }
}