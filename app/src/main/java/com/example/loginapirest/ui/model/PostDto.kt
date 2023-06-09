package com.example.loginapirest.ui.model

import java.time.chrono.ChronoLocalDateTime

data class PostDto(
    val postId: Int,
    val publicationDate: String,
    val saveCount: Int,
    val caption: String,
    val department: Department,
    val category: Category
    )
