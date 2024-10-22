package com.example.baseapp_jetpackcompose.feature.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baseapp_jetpackcompose.core.utils.Utility.getAnimateFloat
import kotlinx.coroutines.delay

@Composable
fun CustomFloatingActionBar(
    navController: NavController
) {
    var isClick by remember { mutableStateOf(false) }

    LaunchedEffect(isClick) {
        if (isClick) {
            delay(800)
            isClick = false
        }
    }

    FloatingActionButton(
        onClick = {
            isClick = true
//            navController.navigate()
        },
        contentColor = Color.White,
        backgroundColor = Color.White,
        shape = CircleShape
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = "Favorite",
            tint = Color.Red,
            modifier = Modifier.size(if (isClick) getAnimateFloat().value.dp else 24.dp),
        )
    }
}