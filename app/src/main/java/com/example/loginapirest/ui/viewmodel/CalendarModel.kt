package com.example.loginapirest.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.loginapirest.ui.model.LoginResponse
import com.example.loginapirest.ui.repository.RepositoryUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalendarModel: ViewModel() {
    var _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()

    data class UIState(
        val _loading: Boolean = false
    )
}
