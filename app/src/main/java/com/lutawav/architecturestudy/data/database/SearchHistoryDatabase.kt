package com.lutawav.architecturestudy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lutawav.architecturestudy.data.database.dao.BlogDao
import com.lutawav.architecturestudy.data.database.dao.ImageDao
import com.lutawav.architecturestudy.data.database.dao.MovieDao
import com.lutawav.architecturestudy.data.database.entity.BlogEntity
import com.lutawav.architecturestudy.data.database.entity.ImageEntity
import com.lutawav.architecturestudy.data.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, BlogEntity::class, ImageEntity::class],
    version = 1
)
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun blogDao(): BlogDao

    abstract fun imageDao(): ImageDao
}
