package com.example.loginapirest.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.ThemedSpinnerAdapter
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginapirest.R
import com.example.loginapirest.ui.config.DataStoreManager
import com.example.loginapirest.ui.model.Usuario
import com.example.loginapirest.ui.navigate.AppScreen
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUsuarioScreen(navController: NavController) {
    val context = LocalContext.current
    val dataStore = DataStoreManager(context)
    val savedValue = dataStore.getValue.collectAsState(initial = "")
    //result = savedValue.toString()
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
            savedValue.value?.let { CreateProfileCard(it) }
        }
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(154.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        color = Color(0, 156,170)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "profile image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )

    }
}

@Composable
private fun CreateInfo(name: String) {
    Column(
        modifier = Modifier
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.Blue,
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyLarge,
            text = name
        )

        Text(
            text = "Androdi Compose Programmer",
            modifier = Modifier.padding(3.dp)
        )

        Text(
            text = "@JetpackCompose",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun CreateProfileCard(name: String) {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        )
        {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider()
                CreateInfo(name)
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
fun BottomBarU(navController: NavController) {
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
