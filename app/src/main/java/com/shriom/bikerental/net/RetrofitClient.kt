package com.shriom.bikerental.net

import com.google.gson.GsonBuilder
import com.shriom.bikerental.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {

        private val mRetrofit: Retrofit
            get() {
                val httpClient = OkHttpClient.Builder()
                httpClient.connectTimeout(1, TimeUnit.MINUTES).writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)

                httpClient.addInterceptor { chain ->
                    val request = chain.request().newBuilder().addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json").build()
                    chain.proceed(request)
                }

                val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .setLenient()
                    .create()

                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create(gson)
                    )

                return retrofitBuilder.client(httpClient.build()).build()
            }

        val mBikeService: BikeService
            get() = mRetrofit.create(BikeService::class.java)

    }

}