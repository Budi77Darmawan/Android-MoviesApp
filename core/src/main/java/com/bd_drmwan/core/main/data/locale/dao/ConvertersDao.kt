package com.bd_drmwan.core.main.data.locale.dao

import androidx.room.TypeConverter
import com.bd_drmwan.core.main.data.locale.entity.GenreEntity
import com.bd_drmwan.core.main.data.locale.entity.ListGenre
import com.google.gson.Gson

class ConvertersDao {
    @TypeConverter
    fun toString(list: List<String>?): String? {
        return list?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toList(str: String?): List<String>? {
        return str?.split(",")
    }

    @TypeConverter
    fun toStringFromGenre(genre: List<GenreEntity>): String {
        val temp = ListGenre(genre)
        return Gson().toJson(temp)
    }

    @TypeConverter
    fun toGenreFromString(str: String): List<GenreEntity> {
        return Gson().fromJson(str, ListGenre::class.java).value
    }
}