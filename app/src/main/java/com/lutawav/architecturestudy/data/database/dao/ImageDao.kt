package com.lutawav.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lutawav.architecturestudy.data.database.entity.ImageEntity

interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<ImageEntity>)

    @Query("SELECT * from image")
    suspend fun getAll(): List<ImageEntity>

    @Query("DELETE from image")
    suspend fun clearAll()
}