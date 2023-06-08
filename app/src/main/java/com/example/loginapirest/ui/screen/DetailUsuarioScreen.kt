package com.example.loginapirest.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginapirest.ui.config.DataStoreManager
import com.example.loginapirest.ui.model.Usuario
import com.example.loginapirest.ui.navigate.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUsuarioScreen(navController: NavController, result: String) {
    val context = LocalContext.current
    val dataStore = DataStoreManager(context)
    val savedValue = dataStore.getValue.collectAsState(initial = "")
    Log.d("SAVED VALUE",savedValue.value.toString())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "User Profile",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = (Color(0, 156, 170)))
            )
        },
        bottomBar = {
            BottomBarU(navController = navController)
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)){
            if (result != null) {
                Text(text = "usuario: ${savedValue}")
            }
        }
    }
}

suspend fun split(ds: DataStoreManager) {
    val one = ds.getValue
    val list = one.toString().split("*")
}

suspend fun checkSize(ds: DataStoreManager, list: List<String>): String {
    val one: String
    if (list.size > 2) {
        one = list.get(0) + "*" + list.get(1) + "*" + list.get(2)
    }

    else {
        one = list.get(0) + "*" + list.get(1)
    }

    ds.saveValue(one)

    return one
}

@Composable
fun BottomBarU(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar() {
        NavigationBarItem(
            label = {
                Text(text = "Feed")
            },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Navigation Icon")
            },
            selected = currentDestination?.hierarchy?.any {
                it.route == AppScreen.ListPost.route
            } == true,
            onClick = {
                navController.navigate(AppScreen.ListPost.route)
            }
        )
        NavigationBarItem(
            label = {
                Text(text = "Calendar")
            },
            icon = {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Navigation Icon")
            },
            selected = currentDestination?.hierarchy?.any {
                it.route == AppScreen.Calendar.route
            } == true,
            onClick = {
                navController.navigate(AppScreen.Calendar.route)
            }
        )
        NavigationBarItem(
            label = {
                Text(text = "Profile")
            },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Navigation Icon")
            },
            selected = currentDestination?.hierarchy?.any {
                it.route == AppScreen.DetailUsuario.route
            } == true,
            onClick = {
                navController.navigate(AppScreen.DetailUsuario.route)
            }
        )
    }
}
