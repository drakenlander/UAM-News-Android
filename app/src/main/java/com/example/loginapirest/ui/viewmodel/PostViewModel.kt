package com.example.loginapirest.ui.viewmodel

import android.database.sqlite.SQLiteBindOrColumnIndexOutOfRangeException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.repository.RepositoryPost
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    val repositoryPost: RepositoryPost = RepositoryPost()

    private val _postState = MutableStateFlow<UIState>(UIState())
    val postState: StateFlow<UIState> = _postState

    init {
        viewModelScope.launch {
            _postState.update { it.copy(_loading = true) }
            val response =  repositoryPost.getAll()
            _postState.update { it.copy(listPost = response) }
            _postState.update { it.copy(_loading = false) }
        }
    }

    data class UIState(
        val _loading: Boolean = false,
        val listPost: List<PostItem> = emptyList()
    )
}
