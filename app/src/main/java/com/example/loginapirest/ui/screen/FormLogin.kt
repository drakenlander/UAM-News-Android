package com.example.loginapirest.ui.screen

import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.loginapirest.R
import com.example.loginapirest.ui.activity.SimpleAlertDialogue
import com.example.loginapirest.ui.viewmodel.LoginModel
import com.example.loginapirest.ui.navigate.AppScreen
import com.example.loginapirest.ui.navigate.AppNavigate

@Composable
fun Circular() {
    CircularProgressIndicator()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun emailField(loginModel: LoginModel) {
    TextField(value = loginModel.email,
        onValueChange = { loginModel.email = it },
        label = { Text(text = "email") },
        singleLine = true,
        maxLines = 1
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun passwordField(loginModel: LoginModel) {
    var passwordVisibility by rememberSaveable{ mutableStateOf(false) }
    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.ic_design_visibility)
    }

    else {
        painterResource(id = R.drawable.ic_design_visibility_off)
    }

    TextField(value = loginModel.password,
        onValueChange = { loginModel.password = it },
        label = { Text(text = "password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = ! passwordVisibility }) {
                Icon(painter = icon, contentDescription =null )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        }
        else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun button(loginModel: LoginModel) {
    Button(onClick = loginModel::onSummit,
        modifier = Modifier.padding(top = 16.dp)) {
        Text(text = "OK")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun formLogin(navController: NavHostController){
    //val state = loginModel._state
    val loginModel : LoginModel = viewModel()
    val context = LocalContext.current
    val state by loginModel.state.collectAsState()
    val isLoading = remember { mutableStateOf(false) }
    val isSuccess = remember { mutableStateOf(false) }
    var show by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(state) {
        isLoading.value = state._loading
        Log.d("LOADING...", isLoading.toString())
        isSuccess.value = state.loginResponse.success
        Log.d("SUCCESS!", isSuccess.toString())
    }

    if (isLoading.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    }

    if (isSuccess.value) {
        Log.d("200", "$loginModel.email")
        SimpleAlertDialogue("200", "$loginModel.email.value") {
            //navController.navigate(route = AppScreen.ListLibro.route)
        }
    }

    else {
        Log.d("404", "ERROR")
        SimpleAlertDialogue(title = ("404"), msg = "$loginModel.email.value") {
            //QWERTY
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        emailField(loginModel)
        Spacer(modifier = Modifier.height(16.dp))
        passwordField(loginModel)
        Spacer(modifier = Modifier.height(16.dp))
        button(loginModel)
    }
}
