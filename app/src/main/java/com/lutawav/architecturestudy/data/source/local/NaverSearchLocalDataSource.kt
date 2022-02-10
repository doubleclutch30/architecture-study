package com.lutawav.architecturestudy.data.source.local

import android.content.SharedPreferences
import com.lutawav.architecturestudy.data.database.SearchHistoryDatabase
import com.lutawav.architecturestudy.data.database.entity.BlogEntity
import com.lutawav.architecturestudy.data.database.entity.ImageEntity
import com.lutawav.architecturestudy.data.database.entity.MovieEntity
import com.lutawav.architecturestudy.data.model.BlogLocalData
import com.lutawav.architecturestudy.data.model.ImageLocalData
import com.lutawav.architecturestudy.data.model.MovieLocalData
import io.reactivex.Single

interface NaverSearchLocalDataSource {

    fun getMovie(): Single<MovieLocalData>
    fun getImage(): Single<ImageLocalData>
    fun getBlog(): Single<BlogLocalData>

    fun saveMovieResult(movies: List<MovieEntity>)
    fun saveImageResult(images: List<ImageEntity>)
    fun saveBlogResult(blogs: List<BlogEntity>)

    fun clearMovieResult()
    fun clearImageResult()
    fun clearBlogResult()

    fun saveMovieKeyword(keyword: String)
    fun saveImageKeyword(keyword: String)
    fun saveBlogKeyword(keyword: String)

    fun getLatestMovieKeyword(): String
    fun getLatestImageKeyword(): String
    fun getLatestBlogKeyword(): String

    fun clearMovieKeyword()
    fun clearImageKeyword()
    fun clearBlogKeyword()

    companion object {
        const val PREFS_NAME = "search_history"
        const val PREFS_KEY_MOVIE = "movie"
        const val PREFS_KEY_BLOG = "blog"
        const val PREFS_KEY_IMAGE = "image"
    }
}