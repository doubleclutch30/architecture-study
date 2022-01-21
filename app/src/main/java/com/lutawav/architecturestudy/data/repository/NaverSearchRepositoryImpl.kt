package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import io.reactivex.Single

class NaverSearchRepositoryImpl : NaverSearchRepository {

    override val naverSearchRemoteDataSource: NaverSearchRemoteDataSource by lazy {
        NaverSearchRemoteDataSourceImpl()
    }

    override fun getMovie(
        keyword: String
    ): Single<ResponseMovie> =
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword
        )

    override fun getBlog(
        keyword: String
    ): Single<ResponseBlog> =
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword
        )

    override fun getImage(
        keyword: String
    ): Single<ResponseImage> =
        naverSearchRemoteDataSource.getImage(
            keyword = keyword
        )

}