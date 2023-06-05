package com.example.loginapirest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapirest.ui.model.PostDto
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.repository.RepositoryPost
import com.example.loginapirest.ui.repository.RepositoryUsusario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostItemViewModel: ViewModel() {
    var _state = MutableStateFlow(false)
    val state: StateFlow<Boolean> = _state

    /*var _state = MutableStateFlow(UIState())
    val state : StateFlow<UIState> = _state.asStateFlow()*/

    val repositoryPost = RepositoryPost()

    fun addPost(item: PostDto) {
        viewModelScope.launch {
            repositoryPost.addPost(item)
            _state.update { true }
        }

        /*
         viewModelScope.launch {
             _state.update { it.copy(_loading = true) }
             val login = repository.fetchData(name,password).getOrDefault(LoginResponse())
             _state.update{it.copy(loginResponse = login)}
             _state.update { it.copy(_loading = false) }
         }*/
    }
}
