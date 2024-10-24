package com.example.baseapp_jetpackcompose.feature.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.baseapp_jetpackcompose.R
import com.example.baseapp_jetpackcompose.feature.character.presentation.navigation.characterNavigationRoute

enum class BottomNav(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    CHARACTERS(
        characterNavigationRoute,
        R.drawable.ic_outline_people,
        R.string.characters_screen_title,
    ),
}