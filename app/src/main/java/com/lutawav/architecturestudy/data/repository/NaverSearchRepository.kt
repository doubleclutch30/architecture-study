package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import io.reactivex.Single

interface NaverSearchRepository {

    val naverSearchRemoteDataSource: NaverSearchRemoteDataSource

    fun getMovie(keyword: String) : Single<ResponseMovie>

    fun getBlog(keyword: String) : Single<ResponseBlog>

    fun getImage(keyword: String) : Single<ResponseImage>
}