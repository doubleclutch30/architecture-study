package com.lutawav.architecturestudy.data.source.remote

import com.lutawav.architecturestudy.data.model.*

interface NaverSearchRemoteDataSource {
    fun getMovie(keyword: String, success: (ResponseMovie) -> Unit, fail: (Throwable) -> Unit)

    fun getBlog(keyword: String, success: (ResponseBlog) -> Unit, fail: (Throwable) -> Unit)

    fun getImage(keyword: String, success: (ResponseImage) -> Unit, fail: (Throwable) -> Unit)

}