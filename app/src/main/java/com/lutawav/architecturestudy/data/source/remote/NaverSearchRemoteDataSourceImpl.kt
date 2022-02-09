package com.lutawav.architecturestudy.data.source.remote

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.network.NaverApi
import io.reactivex.Single

class NaverSearchRemoteDataSourceImpl(
    private val naverApi: NaverApi
) : NaverSearchRemoteDataSource {

    override fun getMovie(
        keyword: String
    ): Single<ResponseMovie> =
        naverApi.getMovie(keyword)

    override fun getBlog(
        keyword: String
    ): Single<ResponseBlog> =
        naverApi.getBlog(keyword)

    override fun getImage(
        keyword: String
    ): Single<ResponseImage> =
        naverApi.getImage(keyword)
}