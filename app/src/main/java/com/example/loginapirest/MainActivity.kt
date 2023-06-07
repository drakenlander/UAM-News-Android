package com.example.loginapirest

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.loginapirest.ui.viewmodel.LoginModel
import com.example.loginapirest.ui.theme.LoginApiRestTheme
import com.example.loginapirest.ui.navigate.AppNavigate

class MainActivity(): ComponentActivity() {
    private val loginModel by viewModels<LoginModel>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginApiRestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigate()
                }
            }
        }
    }
}
