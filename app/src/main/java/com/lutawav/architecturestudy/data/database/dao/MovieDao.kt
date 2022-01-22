package com.lutawav.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lutawav.architecturestudy.data.database.entity.MovieEntity

interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<MovieEntity>

    @Query("DELETE FROM movie")
    suspend fun clearAll()
}