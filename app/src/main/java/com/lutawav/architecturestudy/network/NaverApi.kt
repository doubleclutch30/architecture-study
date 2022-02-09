package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import io.reactivex.Single

class NaverApi(
    private val apiService: ApiService
) {
    fun getMovie(keyword: String): Single<ResponseMovie> =
        apiService
            .getMovie(
                query = keyword
            )
            .map { ResponseMovie(it.items) }

    fun getBlog(keyword: String): Single<ResponseBlog> =
        apiService
            .getBlog(
                query = keyword
            )
            .map { ResponseBlog(it.items) }

    fun getImage(keyword: String): Single<ResponseImage> =
        apiService
            .getImage(
                query = keyword
            )
            .map { ResponseImage(it.items) }
}