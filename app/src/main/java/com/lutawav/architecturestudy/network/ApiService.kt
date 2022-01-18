package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.data.model.Movie
import com.lutawav.architecturestudy.data.model.NaverQueryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Call<NaverQueryResponse<Movie>>

    @GET("v1/search/blog.json")
    fun getBlog(
        @Query("query") query: String
    ): Call<NaverQueryResponse<Blog>>

    @GET("v1/search/image.json")
    fun getImage(
        @Query("query") query: String
    ): Call<NaverQueryResponse<Image>>
}