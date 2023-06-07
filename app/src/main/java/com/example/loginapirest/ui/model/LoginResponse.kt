package com.example.loginapirest.ui.model

data class LoginResponse(
    val msg: String,
    val success: Boolean,
    val usuario: Usuario?
)
{
    constructor():this("",false,null)
}