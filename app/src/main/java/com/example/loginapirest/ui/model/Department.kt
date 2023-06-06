package com.example.loginapirest.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Department(
    val deptId: Int,
    val name: String
) : Parcelable
