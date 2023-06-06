package com.example.loginapirest.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.loginapirest.ui.model.PostDto
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.model.UsuarioItem
import com.example.loginapirest.ui.navigate.AppScreen
import com.example.loginapirest.ui.viewmodel.PostItemViewModel
import com.example.loginapirest.ui.viewmodel.PostViewModel
import com.example.loginapirest.ui.viewmodel.UsuarioItemViewModel
import com.example.loginapirest.ui.viewmodel.UsuarioViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailUsuarioScreen(navController: NavController, usuarioItem: UsuarioItem, oper: String?) {
    var item  by remember { mutableStateOf(usuarioItem) }
    var detail: UsuarioItemViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = item.name, //check
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
                )
            }
        ) { padding ->
            if (oper.equals("1")) {
                DetailBodyContent(navController, item, modifier = Modifier.padding(padding), detail)
            }
        }
    }
}

@Composable
fun DetailBodyContent(navController: NavController, item: UsuarioItem, modifier: Modifier, detail: UsuarioItemViewModel) {

}
