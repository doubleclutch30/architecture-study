package com.lutawav.architecturestudy.module

import com.lutawav.architecturestudy.network.ApiService
import com.lutawav.architecturestudy.network.Url
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Url.NAVER_API_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single {
        OkHttpClient.Builder()
            .run {
                addInterceptor(get<Interceptor>())
                build()
            }
    }

    single {
        Interceptor { chain ->
            with(chain) {
                val newRequest = request().newBuilder()
                    .addHeader("X-Naver-Client-Id", "tr6M7jBKez2OeO2BOXSg")
                    .addHeader("X-Naver-Client-Secret", "S_DUMEv030")
                    .build()
                proceed(newRequest)
            }
        }
    }
}