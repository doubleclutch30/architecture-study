package com.lutawav.architecturestudy.data.source.remote

import android.util.Log
import com.lutawav.architecturestudy.data.model.*
import com.lutawav.architecturestudy.network.NaverApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {

    override fun getMovie(
        keyword: String,
        success: (ResponseMovie) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NaverApi.getMovies(keyword)
            .enqueue(object : Callback<NaverQueryResponse<Movie>> {
                override fun onResponse(
                    call: Call<NaverQueryResponse<Movie>>,
                    response: Response<NaverQueryResponse<Movie>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(ResponseMovie(it.items)) }
                    }
                }

                override fun onFailure(call: Call<NaverQueryResponse<Movie>>, t: Throwable) {
                    Log.e("Movie", "error=${t.message}")
                    fail(t)
                }
            })
    }

    override fun getBlog(
        keyword: String,
        success: (ResponseBlog) ->
        Unit, fail: (Throwable) -> Unit
    ) {
        NaverApi.getBlog(keyword)
            .enqueue(object : Callback<NaverQueryResponse<Blog>> {
                override fun onResponse(
                    call: Call<NaverQueryResponse<Blog>>,
                    response: Response<NaverQueryResponse<Blog>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(ResponseBlog(it.items)) }
                    }
                }

                override fun onFailure(call: Call<NaverQueryResponse<Blog>>, t: Throwable) {
                    Log.e("Blog", "error=${t.message}")
                    fail(t)
                }
            })
    }

    override fun getImage(
        keyword: String,
        success: (ResponseImage) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NaverApi.getImage(keyword)
            .enqueue(object : Callback<NaverQueryResponse<Image>> {
                override fun onResponse(
                    call: Call<NaverQueryResponse<Image>>,
                    response: Response<NaverQueryResponse<Image>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(ResponseImage(it.items)) }
                    }
                }

                override fun onFailure(call: Call<NaverQueryResponse<Image>>, t: Throwable) {
                    Log.e("Blog", "error=${t.message}")
                    fail(t)
                }
            })
    }

}