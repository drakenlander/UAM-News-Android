package com.example.loginapirest.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapirest.ui.theme.LoginApiRestTheme
import com.example.loginapirest.ui.viewmodel.PostViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginapirest.ui.navigate.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPostScreen(navController: NavController) {
    val postViewModel: PostViewModel = viewModel()
    val state by postViewModel.postState.collectAsState()

    LoginApiRestTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Main Feed",
                                color = Color.White
                            )
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = (Color(0, 156, 170))),
                        )
                },
                bottomBar = {
                    BottomBarLPS(navController = navController)
                }
            ) { padding ->
                Posts(state, modifier = Modifier.padding(padding), navController)
            }
        }
    }
}

@Composable
fun BottomBarLPS(navController: NavController) {
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

@Composable
fun Posts(state: PostViewModel.UIState, modifier: Modifier, navController: NavController) {
    if (state._loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
        ) {
            CircularProgressIndicator()
        }
    }

    if (!state.listPost.isEmpty()) {
        LazyColumn(
            modifier = modifier
        ) {
            itemsIndexed(items = state.listPost) { index, item ->
                PostItem(postItem = item, navController, modifier)
                Divider(color = Color.Black, thickness = 1.dp)
                //Text(text = item.publicationDate)
                //Text(text = item.saveCount.toString())
                //Text(text = item.caption)
                //Text(text = item.department.name.toString())
                //Text(text = item.category.name.toString())
            }
        }
    }
}
