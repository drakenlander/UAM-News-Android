package com.example.loginapirest.ui.model

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Parcelize
@Serializable
data class PostItem(
    val postId: Int,
    //val publicationDate: ,
    val saveCount: Int,
    val caption: String,
    val department: String,
    val category: String
): Parcelable
