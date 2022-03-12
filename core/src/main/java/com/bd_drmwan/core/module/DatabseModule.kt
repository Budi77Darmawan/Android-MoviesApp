package com.bd_drmwan.core.module

import android.content.Context
import androidx.room.Room
import com.bd_drmwan.core.BuildConfig.SQLCIPHER_KEY
import com.bd_drmwan.core.main.data.locale.dao.MoviesDao
import com.bd_drmwan.core.main.data.locale.dao.MoviesRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviesRoomDatabase {
        val passphrase = SQLiteDatabase.getBytes(SQLCIPHER_KEY.toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MoviesRoomDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMoviesDao(database: MoviesRoomDatabase): MoviesDao = database.moviesDao()
}