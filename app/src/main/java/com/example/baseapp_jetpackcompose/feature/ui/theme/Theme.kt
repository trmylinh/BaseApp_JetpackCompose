package com.example.baseapp_jetpackcompose.feature.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColors(
    primary = PaleViolet,
    onPrimary = VeryDarkViolet,
    secondary = LightGrayishViolet90,
    onSecondary = VeryDarkGrayishViolet40,
    error = VerySoftRed,
    onError = VeryDarkRed,
    background = VeryDarkBlue,
    onBackground = LightGrayishMagenta,
    surface = VeryDarkBlue,
    onSurface = LightGrayishMagenta,
)

private val LightColorScheme = lightColors(
    primary = DarkModerateViolet,
    onPrimary = White,
    secondary = VeryDarkGrayishViolet,
    onSecondary = LightGrayishViolet,
    error = StrongRed,
    onError = White,
    background = PaleGray,
    onBackground = VeryDarkBlue,
    surface = White,
    onSurface = LightGrayishMagenta,
)

@Composable
fun BaseApp_JetpackComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}