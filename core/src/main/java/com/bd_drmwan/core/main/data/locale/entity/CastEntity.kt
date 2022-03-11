package com.bd_drmwan.core.main.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CastEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "idMovie")
    val idMovie: Int?,
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "gender")
    val gender: Int?,
    @ColumnInfo(name = "adult")
    val adult: String?,
    @ColumnInfo(name = "popularity")
    val popularity: String?,
    @ColumnInfo(name = "characterName")
    val characterName: String?,
    @ColumnInfo(name = "imageUri")
    val imageUri: String?
)