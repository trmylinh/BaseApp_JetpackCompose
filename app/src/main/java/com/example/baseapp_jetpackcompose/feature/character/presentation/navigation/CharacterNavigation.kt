package com.example.baseapp_jetpackcompose.feature.character.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.baseapp_jetpackcompose.feature.character.domain.model.CharacterDto
import com.example.baseapp_jetpackcompose.feature.character.presentation.CharacterScreen

const val characterNavigationRoute = "character_route"

fun NavController.navigateCharacter(
    navOptions: NavOptions? = null
) {
    this.navigate(characterNavigationRoute, navOptions)
}

fun NavGraphBuilder.characterScreen(navigateToDetail: (CharacterDto?) -> Unit) {
    composable(characterNavigationRoute) {
        CharacterScreen(
            viewModel = hiltViewModel(),
            navigateToDetail = {
//                navigateToDetail.invoke(it)
            },
        )
    }
}