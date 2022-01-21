package com.lutawav.architecturestudy.data.source.remote

import com.lutawav.architecturestudy.data.model.*
import io.reactivex.Single

interface NaverSearchRemoteDataSource {

    fun getMovie(keyword: String) : Single<ResponseMovie>

    fun getBlog(keyword: String) : Single<ResponseBlog>

    fun getImage(keyword: String) : Single<ResponseImage>
}