package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.database.entity.BlogEntity
import com.lutawav.architecturestudy.data.database.entity.ImageEntity
import com.lutawav.architecturestudy.data.database.entity.MovieEntity
import com.lutawav.architecturestudy.data.model.*
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import io.reactivex.Single

interface NaverSearchRepository {

    fun getMovie(keyword: String) : Single<ResponseMovie>
    fun getBlog(keyword: String) : Single<ResponseBlog>
    fun getImage(keyword: String) : Single<ResponseImage>


    fun getLatestMovieResult() : Single<List<Movie>>
    fun getLatestImageResult() : Single<List<Image>>
    fun getLatestBlogResult() : Single<List<Blog>>


    fun saveMovieResult(movies : List<MovieEntity>)
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

}