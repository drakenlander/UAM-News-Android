package com.example.loginapirest.ui.navigate

sealed class AppScreen(val route: String) {
    object FormLogin: AppScreen("login")
    object ListPost: AppScreen("listPost")
    object Calendar: AppScreen("calendar")
    object DetailPost: AppScreen("detailPost")
    object DetailUsuario: AppScreen("detailUsuario")

    enum class NavArg(val key: String) {
        Item("item")
    }
}
