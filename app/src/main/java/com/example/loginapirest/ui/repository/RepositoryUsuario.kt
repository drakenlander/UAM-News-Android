package com.example.loginapirest.ui.repository

import android.util.Log
import com.example.loginapirest.ui.model.Department
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.model.UsuarioDto
import com.example.loginapirest.ui.model.UsuarioItem
import com.example.loginapirest.ui.remote.ApiAdapter
import com.example.loginapirest.ui.remote.ApiUsuario
import com.example.loginapirest.ui.response.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import retrofit2.Response

class RepositoryUsuario: CoroutineScope by MainScope() {
    val apiUsuario: ApiUsuario  = ApiAdapter.getInstance()
        .create(ApiUsuario::class.java)

    suspend fun getById(id: Int): List<UsuarioItem> {
        try {
            val u = apiUsuario.getById(id)

            return u
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }

        val emptyD = Department(0, "") //check
        return emptyList<UsuarioItem>()
    }

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
