package com.example.loginapirest.ui.screen

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loginapirest.ui.navigate.AppScreen
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(navController: NavController) {
    //val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Calendar",
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0, 156, 170))
                )
            },
            bottomBar = {
                BottomBarC(navController = navController)
            }
        ) {
            DatePickerSample()
        }
    }
}

@Composable
fun BottomBarC(navController: NavController){
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


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun DatePickerSample() {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("") }
    var arr = ArrayList<String>()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
            arr.add(mDate.value)
        }, mYear, mMonth, mDay
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            mDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(Color(0, 156, 170)) ) {
            Text(text = "Abrir Calendario de Actividades", color = Color.White)
        }

        Spacer(modifier = Modifier.size(25.dp))

        Text(text = "Fecha del siguiente evento: ${mDate.value}", fontSize = 15.sp, textAlign = TextAlign.Center)
        for (i in arr) {
            Text(text = "${i}")
        }
    }
}
