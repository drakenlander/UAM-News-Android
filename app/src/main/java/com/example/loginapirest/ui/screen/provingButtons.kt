package com.example.loginapirest.ui.screen

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ScaffoldSample() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = { BottomAppBar(backgroundColor = MaterialTheme.colors.primary) { Text("Bottom App Bar") } }
    )
}