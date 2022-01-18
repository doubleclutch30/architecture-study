package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.*
import retrofit2.Call

object NaverApi {

    fun getMovies(keyword: String): Call<NaverQueryResponse<Movie>> =
        ApiClient.getApiService()
            .getMovies(
                query = keyword
            )


    fun getBlog(keyword: String): Call<NaverQueryResponse<Blog>> =
        ApiClient.getApiService()
            .getBlog(
                query = keyword
            )


    fun getImage(keyword: String): Call<NaverQueryResponse<Image>> =
        ApiClient.getApiService()
            .getImage(
                query = keyword
            )

}