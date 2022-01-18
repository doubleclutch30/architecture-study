package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.data.model.NaverQueryResponse
import retrofit2.Call

object NaverApi {

    fun getMovies(keyword: String): Call<NaverQueryResponse<Movie>> =
        ApiClient.apiService
            .getMovies(
                query = keyword
            )


    fun getBlog(keyword: String): Call<NaverQueryResponse<Blog>> =
        ApiClient.apiService
            .getBlog(
                query = keyword
            )


    fun getImage(keyword: String): Call<NaverQueryResponse<Image>> =
        ApiClient.apiService
            .getImage(
                query = keyword
            )

}