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
//import com.example.loginapirest.ui.model.LibroItem
import com.example.loginapirest.ui.screen.formLogin
//import com.example.loginapirest.ui.screen.ListLibroScreen
//import com.example.loginapirest.ui.screen.DetailLibroScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun AppNavigate() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.FormLogin.route) {
        /*composable(route= AppScreen.FormLoginScreen.route) {
            FormLogin(navController)
        }
        composable(route= AppScreen.ListLibroScreen.route){
            ListLibroScreen (onItemClick = {
                navController.navigate(AppScreen.DetailLibroScreen.createRoute(it))}
            );
        }
        composable(route= AppScreen.DetailLibroScreen.route
            , arguments = listOf( navArgument(NavArg.Item.key) { type = NavType.parcelableTypeOf<LibroItem>() }) )
        {
            navBackStackEntry -> var item = navBackStackEntry.arguments?.get("item")
            DetailLibroScreen(navController, item as LibroItem)
        }*/

        composable(route= AppScreen.FormLogin.route) {
            formLogin(navController)
        }

        /*composable(route= AppScreen.ListLibro.route){
            ListLibroScreen (navController)

        }
        composable(route= AppScreen.DetailLibro.route + "/{oper}",
            arguments = listOf(navArgument(name="oper") {type= NavType.StringType}))
        {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<LibroItem>("item")
            if (result != null) {
                DetailLibroScreen(navController,result,it.arguments?.getString("oper"))
            }
        }*/
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
