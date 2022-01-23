package com.lutawav.architecturestudy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lutawav.architecturestudy.data.database.entity.MovieEntity
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<MovieEntity>>

    @Query("DELETE FROM movie")
    fun clearAll()
}