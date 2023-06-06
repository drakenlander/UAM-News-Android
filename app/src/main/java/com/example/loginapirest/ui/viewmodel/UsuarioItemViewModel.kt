package com.example.loginapirest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapirest.ui.model.PostDto
import com.example.loginapirest.ui.model.UsuarioDto
import com.example.loginapirest.ui.model.UsuarioItem
import com.example.loginapirest.ui.repository.RepositoryUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioItemViewModel: ViewModel() {
    var _state = MutableStateFlow(false)
    val state: StateFlow<Boolean> = _state

    val repositoryUsuario = RepositoryUsuario()

    fun getById(item: UsuarioDto) {
        val id = item.userId // important
        viewModelScope.launch {
            repositoryUsuario.getById(id)
            _state.update { true }
        }
    }
}
