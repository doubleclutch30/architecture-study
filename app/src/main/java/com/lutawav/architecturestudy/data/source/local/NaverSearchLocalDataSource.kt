package com.lutawav.architecturestudy.data.source.local

import android.content.SharedPreferences
import com.lutawav.architecturestudy.data.database.SearchHistoryDatabase
import com.lutawav.architecturestudy.data.database.entity.BlogEntity
import com.lutawav.architecturestudy.data.database.entity.ImageEntity
import com.lutawav.architecturestudy.data.database.entity.MovieEntity
import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.data.model.Movie
import io.reactivex.Single

interface NaverSearchLocalDataSource {

    val searchHistoryDatabase: SearchHistoryDatabase
    val sharedPreferences : SharedPreferences

    fun getMovie() : Single<List<Movie>>
    fun getImage() : Single<List<Image>>
    fun getBlog() : Single<List<Blog>>

    fun saveMovieResult(movies: List<MovieEntity>)
    fun saveImageResult(images: List<ImageEntity>)
    fun saveBlogResult(blogs: List<BlogEntity>)

    fun clearMovieResult()
    fun clearImageResult()
    fun clearBlogResult()

    fun saveMovieKeyword(keyword: String)
    fun saveImageKeyword(keyword: String)
    fun saveBlogKeyword(keyword: String)

    fun getLatestMovieKeyword() : String
    fun getLatestImageKeyword() : String
    fun getLatestBlogKeyword() : String

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