package com.example.loginapirest.ui.navigate

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.screen.DetailPostScreen
import com.example.loginapirest.ui.screen.ListPostScreen
import com.example.loginapirest.ui.screen.formLogin
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun AppNavigate() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.FormLogin.route) {
        composable(route= AppScreen.FormLogin.route) {
            formLogin(navController)
        }

        composable(route = AppScreen.ListPost.route) {
            ListPostScreen (navController)
        }

        composable(route= AppScreen.DetailPost.route + "/{oper}",
            arguments = listOf(navArgument(name = "oper") { type = NavType.StringType }))
        {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<PostItem>("item")
            if (result != null) {
                DetailPostScreen(navController, result, it.arguments?.getString("oper"))
            }
        }
    }
}

inline fun <reified T: Parcelable> NavType.Companion.parcelableTypeOf() = object: NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        throw Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key,value)
    }
}
