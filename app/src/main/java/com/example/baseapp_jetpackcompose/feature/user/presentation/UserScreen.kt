package com.example.baseapp_jetpackcompose.feature.user.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter

@Composable
fun UserScreen(viewModel: UserViewModel = hiltViewModel()){
    val user by viewModel.responseUser.collectAsState()

    if (user == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Card(
            modifier = Modifier.size(200.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(user?.image.orEmpty()),
                        contentScale = ContentScale.Fit,
                        contentDescription = "User Image",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(shape = RoundedCornerShape(15)),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.size(80.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            text = user?.name.orEmpty(),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primary,
                        )
                        Text(
                            text = user?.gender.orEmpty(),
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.primary,
                        )
                    }
                }
            }
        }
    }
}
