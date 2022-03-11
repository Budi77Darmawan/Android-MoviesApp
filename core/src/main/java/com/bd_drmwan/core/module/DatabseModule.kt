package com.bd_drmwan.core.module

import android.content.Context
import androidx.room.Room
import com.bd_drmwan.core.main.data.locale.dao.MoviesDao
import com.bd_drmwan.core.main.data.locale.dao.MoviesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviesRoomDatabase =
        Room.databaseBuilder(
            context,
            MoviesRoomDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMoviesDao(database: MoviesRoomDatabase): MoviesDao = database.moviesDao()
}