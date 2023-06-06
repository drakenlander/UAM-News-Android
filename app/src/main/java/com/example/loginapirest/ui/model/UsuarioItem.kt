package com.example.loginapirest.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class UsuarioItem(
    val usId: Int,
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String,
    val department: Department
) : Parcelable
