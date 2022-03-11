package com.bd_drmwan.core.module

import com.bd_drmwan.core.main.data.locale.source.LocaleDataSource
import com.bd_drmwan.core.main.data.remote.source.CastRemoteDataSource
import com.bd_drmwan.core.main.data.remote.source.MoviesRemoteDataSource
import com.bd_drmwan.core.main.domain.repository.*
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
        remoteDataSource: CastRemoteDataSource
    ): ICastRepository {
        return CastRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideLocaleRepository(
        localeDataSource: LocaleDataSource
    ): ILocaleRepository {
        return LocaleRepositoryImpl(localeDataSource)
    }
}