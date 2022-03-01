package com.bd_drmwan.core.module

import com.bd_drmwan.core.main.data.remote.source.MoviesRemoteDataSource
import com.bd_drmwan.core.main.services.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(
        service: MoviesService
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSource(service)
    }
}
