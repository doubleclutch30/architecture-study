package com.lutawav.architecturestudy.data.source.remote

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.network.NaverApi
import io.reactivex.Single

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {

    override fun getMovie(
        keyword: String
    ): Single<ResponseMovie> =
        NaverApi.getMovie(keyword)

    override fun getBlog(
        keyword: String
    ): Single<ResponseBlog> =
        NaverApi.getBlog(keyword)

    override fun getImage(
        keyword: String
    ): Single<ResponseImage> =
        NaverApi.getImage(keyword)
}