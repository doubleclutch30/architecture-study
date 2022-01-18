package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl

class NaverSearchRepositoryImpl: NaverSearchRepository {

    override val naverSearchRemoteDataSource: NaverSearchRemoteDataSource by lazy {
        NaverSearchRemoteDataSourceImpl()
    }

    override fun getMovie(
        keyword: String,
        success: (ResponseMovie) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getBlog(
        keyword: String,
        success: (ResponseBlog) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }

    override fun getImage(
        keyword: String,
        success: (ResponseImage) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverSearchRemoteDataSource.getImage(
            keyword = keyword,
            success = success,
            fail = fail
        )
    }
}