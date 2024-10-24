package com.example.baseapp_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.baseapp_jetpackcompose.feature.navigation.NavGraph
import com.example.baseapp_jetpackcompose.feature.ui.theme.BaseApp_JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// them annotation @AndroidEntryPoint khi su dung Hilt - Activity
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var application: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseApp_JetpackComposeTheme(darkTheme = application.isDark.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BaseApp_JetpackComposeTheme {
        NavGraph()
    }
}