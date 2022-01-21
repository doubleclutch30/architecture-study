package com.lutawav.architecturestudy.network

import android.util.Log
import com.lutawav.architecturestudy.util.peekBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val READ_TIMEOUT_SECONDS = 30
    private const val WRITE_TIMEOUT_SECONDS = 10
    private const val CONNECTION_TIMEOUT_SECONDS = 30

    private const val BASE_URL = "https://openapi.naver.com"

    val apiService: ApiService = getRetrofit(BASE_URL).create()

    private fun getRetrofit(baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private class HeaderInterceptor : Interceptor {
        override fun intercept(
            chain: Interceptor.Chain
        ): Response {
            val original = chain.request()
            val request = original.newBuilder().apply {
                addHeader("X-Naver-Client-Id", "tr6M7jBKez2OeO2BOXSg")
                addHeader("X-Naver-Client-Secret", "S_DUMEv030")
            }.build()

            val response = chain.proceed(request)
            val statusCode = response.code
            if (statusCode == 200) {
                Log.i("ApiClient", "res=${response}, body=${response.peekBody()}")
            }

            NetworkException.create(response)?.run { throw this }

            return response
        }
    }


}