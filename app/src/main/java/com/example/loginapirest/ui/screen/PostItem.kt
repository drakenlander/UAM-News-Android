package com.example.loginapirest.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.loginapirest.R
import com.example.loginapirest.ui.model.PostItem
import com.example.loginapirest.ui.navigate.AppScreen

@Composable
fun PostItem(postItem: PostItem, navController: NavController, modifier: Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier.background(Color(216, 220, 227)),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = postItem.publicationDate, fontWeight = FontWeight.Bold)
            Text(text = postItem.caption,
                modifier = Modifier
                .clickable {
                    navController.currentBackStackEntry?.savedStateHandle?.set("item", postItem)
                    navController.navigate(AppScreen.DetailPost.route + "/DETAIL")
                },
                fontWeight = FontWeight.ExtraBold
            )
            Row(
                horizontalArrangement = Arrangement.End) {
                Row() {
                    Text(text = "Le gusta a " + postItem.saveCount.toString() + " personas")
                }
                IconButton(onClick = { }) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        tint = Color.Black,
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "like border"
                    )
                }
                IconButton(onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("item", postItem)
                    navController.navigate(AppScreen.DetailPost.route + "/UPDATE")}) {
                    Icon(painter = painterResource(R.drawable.ic_desing_libro_update), contentDescription = null )
                }
            }

        }
    }
}
