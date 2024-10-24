package com.example.baseapp_jetpackcompose.feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.baseapp_jetpackcompose.feature.character.presentation.navigation.characterNavigationRoute
import com.example.baseapp_jetpackcompose.feature.character.presentation.navigation.characterScreen
import com.example.baseapp_jetpackcompose.feature.component.CustomBottomBar
import com.example.baseapp_jetpackcompose.feature.component.CustomFloatingActionBar
import com.example.baseapp_jetpackcompose.feature.component.CustomScaffold

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    CustomScaffold(
        bottomBar = {
            BottomNav.entries.forEach { navItem ->
                if (navItem.route == currentRoute) {
                    CustomBottomBar(
                        navController = navController,
                        currentDestination = currentDestination,
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNav.entries.forEach { navItem ->
                if (navItem.route == currentRoute) {
                    CustomFloatingActionBar(navController = navController)
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = characterNavigationRoute,
            modifier = Modifier.padding(innerPadding),
        ){
            characterScreen {}
        }
    }

}