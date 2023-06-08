package com.example.loginapirest.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginapirest.R
import com.example.loginapirest.ui.config.DataStoreManager
import com.example.loginapirest.ui.navigate.AppScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUsuarioScreen(navController: NavController) {
    val context = LocalContext.current
    /*val dataStoreEmail = DataStoreManager(context)
    val savedEMail = dataStoreEmail.getValue.collectAsState(initial = "")
    val dataStoreDeptName = DataStoreManager(context)
    val savedDeptName = dataStoreDeptName.getValue.collectAsState(initial = "")
    val dataStoreName = DataStoreManager(context)
    val savedName = dataStoreName.getValue.collectAsState(initial = "")*/


    val dataStore = DataStoreManager(context)
    val savedData = dataStore.getValue.collectAsState(initial = "")

    var list: List<String> = savedData.value?.let { split(it) }!!

    var nombre by rememberSaveable { mutableStateOf("")}
    var email by rememberSaveable() { mutableStateOf("") }
    var date by rememberSaveable() { mutableStateOf("") }

    if (list.size == 2) {
        if (list.get(0) != "*"){
            nombre = list.get(0)
            email = list.get(1)
        }
    }

    if (list.size == 3) {
        if (list.get(0) != "*") {
            nombre = list.get(0)
            email = list.get(1)
            date = list.get(2)
        }
    }
    list.get(0)
  //  list.get(1)

    //result = savedValue.toString()
    //Log.d("SAVED VALUE",savedName.toString())
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
            //savedData.value?.let { CreateProfileCard(name = it, email = savedData.value + "@uamv.edu.ni", deptName = "FIA") }
            CreateProfileCard(name = nombre, email = email, deptName = "FIA")
        //savedDeptName.value?.let { savedEMail.value?.let { it1 -> savedName.value?.let { it2 -> CreateProfileCard(name = it2, email = it1, deptName = it) } } }
        }
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(154.dp)
            .padding(25.dp),
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
private fun CreateInfo(name: String, email: String, deptName: String) {
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
            text = email,
            modifier = Modifier.padding(3.dp)
        )

        Text(
            text = "FIA",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun CreateProfileCard(name: String, email: String, deptName: String) {
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
                CreateInfo(name, email, deptName)
            }
        }
    }
}

fun split(ds: String): List<String> {
    //val one = ds.getValue
    val list = ds.split("*")

    return list
}

suspend fun checkSize(ds: DataStoreManager, list: List<String>): String {
    val one: String
    if (list.size > 2) {
        one = list.get(0) + "*" + list.get(1) + "*" + list.get(2)
    }

    else {
        one = list.get(0) + "*" + list.get(1)
    }

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
