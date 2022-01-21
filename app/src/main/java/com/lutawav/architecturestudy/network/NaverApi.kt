package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import com.lutawav.architecturestudy.util.singleIoMainThread
import io.reactivex.Single
import io.reactivex.SingleTransformer

object NaverApi {

    fun getMovie(keyword: String): Single<ResponseMovie> =
        ApiClient.apiService
            .getMovie(
                query = keyword
            )
            .map { ResponseMovie(it.items) }
            .compose(commonNetwork())

    fun getBlog(keyword: String): Single<ResponseBlog> =
        ApiClient.apiService
            .getBlog(
                query = keyword
            )
            .map { ResponseBlog(it.items) }
            .compose(commonNetwork())

    fun getImage(keyword: String): Single<ResponseImage> =
        ApiClient.apiService
            .getImage(
                query = keyword
            )
            .map { ResponseImage(it.items) }
            .compose(commonNetwork())

    private fun <T> commonNetwork(): SingleTransformer<T, T> = SingleTransformer { upstream ->
        upstream
            .compose(singleIoMainThread())
    }
}