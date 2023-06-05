package com.example.loginapirest.ui.navigate

sealed class AppScreen(val route: String) {
    object FormLogin: AppScreen("login")
}
