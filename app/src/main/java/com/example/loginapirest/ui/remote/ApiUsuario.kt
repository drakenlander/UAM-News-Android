package com.example.loginapirest.ui.remote

import com.example.loginapirest.ui.model.UsuarioDto
import com.example.loginapirest.ui.model.UsuarioItem
import com.example.loginapirest.ui.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiUsuario {
    @GET("/originalPoster/login")
    suspend fun getLogin(@Query("email") email: String, @Query("password") password: String): Response<LoginResponse>

    @GET("/originalPoster/get/{id}")
    suspend fun getById(@Query("id") id: Int): UsuarioItem
}
