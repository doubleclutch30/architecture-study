package com.lutawav.architecturestudy.data.source.remote

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import io.reactivex.Single

interface NaverSearchRemoteDataSource {

    fun getMovie(keyword: String): Single<ResponseMovie>

    fun getBlog(keyword: String): Single<ResponseBlog>

    fun getImage(keyword: String): Single<ResponseImage>
}