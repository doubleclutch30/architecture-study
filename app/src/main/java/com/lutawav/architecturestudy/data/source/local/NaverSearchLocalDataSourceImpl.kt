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

class NaverSearchLocalDataSourceImpl : NaverSearchLocalDataSource {

    override val searchHistoryDatabase: SearchHistoryDatabase
        get() = TODO("Not yet implemented")
    override val sharedPreferences: SharedPreferences
        get() = TODO("Not yet implemented")

    override fun getMovie(): Single<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getImage(): Single<List<Image>> {
        TODO("Not yet implemented")
    }

    override fun getBlog(): Single<List<Blog>> {
        TODO("Not yet implemented")
    }

    override fun saveMovieResult(movies: List<MovieEntity>) {
        TODO("Not yet implemented")
    }

    override fun saveImageResult(images: List<ImageEntity>) {
        TODO("Not yet implemented")
    }

    override fun saveBlogResult(blogs: List<BlogEntity>) {
        TODO("Not yet implemented")
    }

    override fun clearMovieResult() {
        TODO("Not yet implemented")
    }

    override fun clearImageResult() {
        TODO("Not yet implemented")
    }

    override fun clearBlogResult() {
        TODO("Not yet implemented")
    }

    override fun saveMovieKeyword(keyword: String) {
        TODO("Not yet implemented")
    }

    override fun saveImageKeyword(keyword: String) {
        TODO("Not yet implemented")
    }

    override fun saveBlogKeyword(keyword: String) {
        TODO("Not yet implemented")
    }

    override fun getLatestMovieKeyword(): String {
        TODO("Not yet implemented")
    }

    override fun getLatestImageKeyword(): String {
        TODO("Not yet implemented")
    }

    override fun getLatestBlogKeyword(): String {
        TODO("Not yet implemented")
    }

    override fun clearMovieKeyword() {
        TODO("Not yet implemented")
    }

    override fun clearImageKeyword() {
        TODO("Not yet implemented")
    }

    override fun clearBlogKeyword() {
        TODO("Not yet implemented")
    }
}