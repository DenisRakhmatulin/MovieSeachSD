package com.example.movieseachsd.model.rest

import com.example.movieseachsd.model.entites.rest_entites.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/{id}")
    fun getMovie(@Path("id") id: String,
                 @Query("api_key") api_key: String = "b92fe7da8ec4b4826719674503a0a65b",
                 @Query("language") language: String = "ru",
    ): Call<MovieDTO>
}