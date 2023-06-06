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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.loginapirest.ui.model.PostDto
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.navigate.AppScreen
import com.example.loginapirest.ui.viewmodel.PostItemViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPostScreen(navController: NavController, postItem: PostItem, oper: String?) {
    var item  by remember { mutableStateOf(postItem) }
    var detail: PostItemViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = item.caption, //check
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
                )
            }
        ) { padding ->
            if (oper.equals("UPDATE")) {
                AddBodyContent(navController, item, modifier = Modifier.padding(padding), detail)
            }

            if (oper.equals("DETAIL")) {
                DetailBodyContent(navController, item, modifier = Modifier.padding(padding), detail)
            }
        }
    }
}

@Composable
fun DetailBodyContent(navController: NavController, item: PostItem, modifier: Modifier, detail: PostItemViewModel) {

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddBodyContent(navController: NavController, item: PostItem, modifier: Modifier, detail: PostItemViewModel) {
    var id by rememberSaveable { mutableStateOf(item.postId) }
    var publicationDate by rememberSaveable { mutableStateOf(item.publicationDate) }
    var saveCount by rememberSaveable { mutableStateOf(item.saveCount) }
    var caption by rememberSaveable { mutableStateOf(item.caption) }
    val department by rememberSaveable { mutableStateOf(item.department) }
    val category by rememberSaveable { mutableStateOf(item.category) }
    val postItemViewModel: PostItemViewModel = viewModel()
    val context = LocalContext.current
    val state by detail.state.collectAsState();

    if (state) {
        navController.navigate(AppScreen.ListPost.route)
    }

    Box( modifier = modifier)
    {
        Card(
            modifier = Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(text = "ID: ${ id.toString() }",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    TextField(value = caption, //check
                        onValueChange = { caption = it },
                        label = { Text (text = "Caption") }, //check
                        singleLine = true,
                        maxLines = 1
                    )
                    Button(onClick = { detail.addPost(PostDto(id, publicationDate, saveCount, caption, department, category)) }
                    )
                    {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}
