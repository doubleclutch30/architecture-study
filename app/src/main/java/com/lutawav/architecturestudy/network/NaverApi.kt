package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.model.ResponseBlog
import com.lutawav.architecturestudy.data.model.ResponseImage
import com.lutawav.architecturestudy.data.model.ResponseMovie
import io.reactivex.Single

object NaverApi {

    fun getMovie(keyword: String): Single<ResponseMovie> =
        ApiClient.apiService
            .getMovie(
                query = keyword
            )
            .map { ResponseMovie(it.items) }


    fun getBlog(keyword: String): Single<ResponseBlog> =
        ApiClient.apiService
            .getBlog(
                query = keyword
            )
            .map { ResponseBlog(it.items) }


    fun getImage(keyword: String): Single<ResponseImage> =
        ApiClient.apiService
            .getImage(
                query = keyword
            )
            .map { ResponseImage(it.items) }

}