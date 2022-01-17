package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.BlogResponse
import com.lutawav.architecturestudy.data.ImageResponse
import com.lutawav.architecturestudy.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object NaverApi {

    fun getMovies(keyword: String) : Call<MovieResponse> {
        return ApiClient.getApiService()
            .getMovies(
                query = keyword
            )
    }

    fun getBlog(keyword: String) : Call<BlogResponse> {
        return ApiClient.getApiService()
            .getBlog(
                query = keyword
            )
    }

    fun getImage(keyword: String) : Call<ImageResponse> {
        return ApiClient.getApiService()
            .getImage(
                query = keyword
            )
    }
}