package com.fancy.instagram.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetrofitCreator {

    companion object {

        private fun instagramRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://192.168.2.5:7001/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build()
        }

        fun <T> create(service: Class<T>): T {
            return instagramRetrofit().create(service)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header(
                            "Authorization",
                            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6InRlc3QiLCJpYXQiOjE1OTM1MDYyODcsImV4cCI6MTU5MzU5MjY4N30.5z9f7UKvJEsHR1dY2bvQv8p9Nydf_zefPWgpSqsDGWM"
                        ) // <-- this is the important line
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()
        }
    }
}