package com.example.loginapirest.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.loginapirest.ui.model.Usuario

/*@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                    },navigationIcon = {
                        IconButton(onClick = { navController.navigate(AppScreen.ListPost.route) },
                            colors= IconButtonDefaults.filledIconButtonColors(contentColor = Color.White)
                        ) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
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
}*/

@Composable
fun DetailUsuarioScreen(navController: NavController, result: Usuario?) {
    Box(){
        if (result != null) {
            Text(text = "usuario:${result.name}")

        }
    }
}
