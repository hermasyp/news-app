package com.catnip.newsapp.data.network

import com.catnip.newsapp.BuildConfig
import com.catnip.newsapp.data.constants.CommonConstants
import com.catnip.newsapp.data.model.news.NewsResponses
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = CommonConstants.COUNTRY,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponses

    companion object {
        @JvmStatic
        operator fun invoke(
            chucker: ChuckerInterceptor,
            gsonConverterFactory: GsonConverterFactory
        ): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chucker)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}