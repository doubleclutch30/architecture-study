package com.lutawav.architecturestudy.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.lutawav.architecturestudy.data.entity.BlogEntity

interface BlogDao {
    @Insert
    suspend fun insertAll(blogs: List<BlogEntity>)

    @Query("SELECT * from blog")
    suspend fun getAll(): List<BlogEntity>

    @Query("DELETE from blog")
    suspend fun clearAll()
}