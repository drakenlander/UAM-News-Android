package com.example.loginapirest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapirest.ui.model.UsuarioDto
import com.example.loginapirest.ui.model.UsuarioItem
import com.example.loginapirest.ui.repository.RepositoryUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioViewModel: ViewModel() {
    val repositoryUsuario: RepositoryUsuario = RepositoryUsuario()

    private val _usuarioState = MutableStateFlow<UIState>(UIState())

    val usuarioState: StateFlow<UIState> = _usuarioState

    init {
        viewModelScope.launch {
            _usuarioState.update { it.copy(_loading = true) }
            val response =  repositoryUsuario.getById(1)
            _usuarioState.update { it.copy(detailUsuario = response) }
            _usuarioState.update { it.copy(_loading = false) }
        }
    }

    data class UIState(
        val _loading: Boolean = false,
        val detailUsuario: List<UsuarioItem> = emptyList()
    )
}
