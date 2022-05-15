package com.example.movieseachsd.model.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchRepo {
    val api: SearchAPI by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(SearchAPI::class.java)
    }
}