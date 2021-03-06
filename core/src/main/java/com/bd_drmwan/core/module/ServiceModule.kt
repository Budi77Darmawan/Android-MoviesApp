package com.bd_drmwan.core.module

import com.bd_drmwan.core.BuildConfig.BASE_URL
import com.bd_drmwan.core.main.services.ActorsService
import com.bd_drmwan.core.main.services.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideMoviesService(
        client: OkHttpClient
    ): MoviesService {
        return NetworkModule
            .buildRetrofit(BASE_URL, client)
            .create(MoviesService::class.java)
    }

    @Singleton
    @Provides
    fun provideActorsService(
        client: OkHttpClient
    ): ActorsService {
        return NetworkModule
            .buildRetrofit(BASE_URL, client)
            .create(ActorsService::class.java)
    }

}