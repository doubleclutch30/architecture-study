package com.lutawav.architecturestudy.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://openapi.naver.com/"

    private var apiService: ApiService? = null

    fun getApiService(): ApiService {
        val service = apiService
            ?: getRetrofit()
                .create(ApiService::class.java)
        apiService = service
        return service
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient(HeaderSettingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideOkHttpClient(
        interceptor: HeaderSettingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .run {
            addInterceptor(interceptor)
            build()
        }


    private class HeaderSettingInterceptor : Interceptor {
        override fun intercept(
            chain: Interceptor.Chain
        ): Response = with(chain) {
            val request = request().newBuilder()
                .addHeader("X-Naver-Client-Id", "tr6M7jBKez2OeO2BOXSg")
                .addHeader("X-Naver-Client-Secret", "S_DUMEv030")
                .build()

            val response = proceed(request)
            Log.i("ApiClient", "res=${response}")

            return response
        }
    }

}