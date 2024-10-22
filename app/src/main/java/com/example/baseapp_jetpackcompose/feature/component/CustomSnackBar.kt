package com.example.baseapp_jetpackcompose.feature.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerArrayResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.baseapp_jetpackcompose.R

sealed class SnackBarEnum(val backgroundColor: Int) {
    object SUCCESS : SnackBarEnum(backgroundColor = R.color.black)
    object ERROR : SnackBarEnum(backgroundColor = R.color.black)
}

@Composable
fun CustomSnackBar(
    snackBarHostState: SnackbarHostState,
    snackBarEnum: SnackBarEnum,
) {
    SnackbarHost(hostState = snackBarHostState) { data ->
        Snackbar(
            snackbarData = data, shape = MaterialTheme.shapes.medium,
            backgroundColor = Color(
                integerResource(id = snackBarEnum.backgroundColor)
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    CustomSnackBar(
        rememberScaffoldState().snackbarHostState,
        SnackBarEnum.SUCCESS
    )
}