package com.lutawav.architecturestudy.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.lutawav.architecturestudy.data.entity.ImageEntity

interface ImageDao {
    @Insert
    suspend fun insertAll(movies: List<ImageEntity>)

    @Query("SELECT * from image")
    suspend fun getAll(): List<ImageEntity>

    @Query("DELETE from image")
    suspend fun clearAll()
}