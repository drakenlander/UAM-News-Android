package com.example.loginapirest.ui.navigate

sealed class AppScreen(val route: String) {
    object FormLogin: AppScreen("login")
    object ListPost: AppScreen("listPost")
    object DetailPost: AppScreen("detailPost")

    enum class NavArg(val key: String) {
        Item("item")
    }
}
