package com.example.fetch_test_app.network

import com.example.fetch_test_app.data.Item
import retrofit2.http.GET

interface FetchApi {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}