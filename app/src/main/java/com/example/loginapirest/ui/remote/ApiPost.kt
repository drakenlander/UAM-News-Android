package com.example.loginapirest.ui.remote

import com.example.loginapirest.ui.model.PostDto
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.model.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiPost {
    @GET("/textPost/all")
    suspend fun getAll(): List<PostItem>

    @POST("/textPost/save/")
    suspend fun save(@Body item: PostDto)
}
