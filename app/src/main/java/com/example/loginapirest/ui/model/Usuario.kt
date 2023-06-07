package com.example.loginapirest.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Usuario(
    val department: Department?,
    val email: String,
    val name: String,
    val password: String,
    val phoneNumber: String,
    val usId: Int
) : Parcelable {
    constructor(): this(null, "", "", "", "", 0)
}