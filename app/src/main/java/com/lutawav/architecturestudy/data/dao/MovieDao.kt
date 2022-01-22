package com.lutawav.architecturestudy.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.lutawav.architecturestudy.data.entity.MovieEntity

interface MovieDao {

    @Insert
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<MovieEntity>

    @Query("DELETE FROM movie")
    suspend fun clearAll()
}