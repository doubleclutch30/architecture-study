package com.lutawav.architecturestudy.network

import com.lutawav.architecturestudy.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/search/movie.json")
    fun getMovies(
        @Query("query") query: String
    ): Call<MovieResponse>
}