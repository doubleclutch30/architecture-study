package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl
import io.reactivex.Single

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {

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