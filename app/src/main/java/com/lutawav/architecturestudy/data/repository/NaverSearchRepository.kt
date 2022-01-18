package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource

interface NaverSearchRepository {


    val naverSearchRemoteDataSource: NaverSearchRemoteDataSource

    fun getMovie(keyword: String, success: (ResponseMovie) -> Unit, fail: (Throwable) -> Unit)

    fun getBlog(keyword: String, success: (ResponseBlog) -> Unit, fail: (Throwable) -> Unit)

    fun getImage(keyword: String, success: (ResponseImage) -> Unit, fail: (Throwable) -> Unit)
}