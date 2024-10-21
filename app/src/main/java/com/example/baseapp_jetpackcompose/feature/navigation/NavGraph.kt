package com.example.baseapp_jetpackcompose.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.baseapp_jetpackcompose.feature.component.CustomScaffold

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    CustomScaffold(
        bottomBar = {
            BottomNav.values().forEach { navItem ->
                if(navItem.route == currentRoute){


                }            }
        }
    ) {

    }

}