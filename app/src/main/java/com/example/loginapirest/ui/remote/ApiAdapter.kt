package com.example.loginapirest.ui.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    val BASE_URL = "http://192.168.1.24:8181"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }
}
