package com.example.loginapirest.ui.repository

import android.util.Log
import com.example.loginapirest.ui.model.PostDto
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.remote.ApiAdapter
import com.example.loginapirest.ui.remote.ApiPost
import retrofit2.Response

class RepositoryPost( ) {
    private val apiPost: ApiPost = ApiAdapter.getInstance()
        .create(ApiPost::class.java)

    suspend fun getAll(): List<PostItem> {
        try {
            val listPost = apiPost.getAll()

            return listPost
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString());
        }

        return emptyList<PostItem>()
    }

    /*suspend fun addPost(item: PostDto)   {
        try
        {
            apiPost.save(item)
        } catch (e: Exception) {
            Log.d("ERROR", e.message.toString())
        }
    }*/
}
