package com.example.loginapirest.ui.repository

import android.util.Log
import com.example.loginapirest.ui.model.Department
import com.example.loginapirest.ui.model.LoginResponse
import com.example.loginapirest.ui.remote.ApiAdapter
import com.example.loginapirest.ui.remote.ApiUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import retrofit2.Response

class RepositoryUsuario: CoroutineScope by MainScope() {
    val apiUsuario: ApiUsuario  = ApiAdapter.getInstance()
        .create(ApiUsuario::class.java)

    suspend fun fetchData(email: String, password: String): Result<LoginResponse> {
        var  loginResponse: LoginResponse = LoginResponse()

        return try {
            val response: Response<LoginResponse> = apiUsuario.getLogin(email, password)
            loginResponse = response.body() as LoginResponse
            Log.d("200", "Connection stablished!, $loginResponse.msg")
            Result.success(loginResponse)

        } catch (e: Exception) {
            Log.d("ERROR", "$e.message")
            Result.failure(e)
        }
    }
}
