package com.example.fetch_test_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hiring.fetch.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: FetchApi = retrofit.create(FetchApi::class.java)
}