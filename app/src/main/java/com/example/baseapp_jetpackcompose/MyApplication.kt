package com.example.baseapp_jetpackcompose

import android.app.Application
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}