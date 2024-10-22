package com.example.baseapp_jetpackcompose.feature.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.example.baseapp_jetpackcompose.R
import com.example.baseapp_jetpackcompose.feature.navigation.BottomNav

@Composable
fun CustomBottomBar(
    navController: NavController,
    currentDestination: NavDestination?,
) {
    BottomAppBar(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp
            )
        ),
        cutoutShape = CircleShape,
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.onSecondary
    ) {
        BottomNav.entries.forEach { screen ->
            val selected = currentDestination.isBottomNavDestinationInHierarchy(screen)
            val primaryColor = MaterialTheme.colors.primary
            val secondaryColor = MaterialTheme.colors.secondary

            BottomNavigationItem(
                selected = selected,
                onClick = { navigateToBottomNavDestination(screen, navController) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = screen.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                },
                alwaysShowLabel = true,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                label = {
                    Text(
                        text = if (screen.title == R.string.favorite_screen_title) "" else stringResource(
                            id = screen.title
                        ),
                        color = if (selected) primaryColor else secondaryColor,
                        style = MaterialTheme.typography.overline,
                        textAlign = TextAlign.Center, maxLines = 1,
                    )
                },
            )
        }

    }
}

fun navigateToBottomNavDestination(bottomNav: BottomNav, navController: NavController) {
        val bottomNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true

            restoreState = true
        }

        when (bottomNav) {
//            BottomNav.CHARACTERS -> navController.navigateCharacter(bottomNavOptions)
//            BottomNav.EPISODES -> navController.navigateToEpisodes(bottomNavOptions)
//            BottomNav.FAVORITES -> navController.navigateToFavorites(bottomNavOptions)
//            BottomNav.SEARCH -> navController.navigateToSearch(bottomNavOptions)
//            BottomNav.SETTINGS -> navController.navigateToSettings(bottomNavOptions)
            else -> {}
        }
}

private fun NavDestination?.isBottomNavDestinationInHierarchy(destination: BottomNav) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false