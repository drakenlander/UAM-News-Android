package com.example.loginapirest.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Category(
    val catId: Int,
    val name: String
) : Parcelable
