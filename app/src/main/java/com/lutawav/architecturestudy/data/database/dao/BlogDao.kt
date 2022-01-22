package com.lutawav.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lutawav.architecturestudy.data.database.entity.BlogEntity

interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(blogs: List<BlogEntity>)

    @Query("SELECT * from blog")
    suspend fun getAll(): List<BlogEntity>

    @Query("DELETE from blog")
    suspend fun clearAll()
}