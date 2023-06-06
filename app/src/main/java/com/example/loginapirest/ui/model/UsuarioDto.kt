package com.example.loginapirest.ui.model

data class UsuarioDto(
    val userId: Int,
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val department: Department
)
