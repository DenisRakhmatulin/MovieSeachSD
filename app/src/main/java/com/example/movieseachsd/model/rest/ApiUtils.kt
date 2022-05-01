package com.example.movieseachsd.model.rest

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiUtils {
    private const val baseUrlMainPart = "https://api.themoviedb.org/"
    private const val baseUrlVersion = "3/"
    val baseUrl = "$baseUrlMainPart$baseUrlVersion"

    fun getOkHTTPBuilderWithHeaders(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)
        /* httpClient.addInterceptor { chain ->
             val original = chain.request()
             val request = original.newBuilder()
                 .method(original.method(), original.body())
                 .header("api_key", "b92fe7da8ec4b4826719674503a0a65b")
                 .header("language", "ru")
                 .build()

             chain.proceed(request)
         }*/
        return httpClient.build()
    }
}