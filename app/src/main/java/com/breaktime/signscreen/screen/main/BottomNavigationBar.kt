package com.breaktime.signscreen.screen.main

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.breaktime.signscreen.R
import com.breaktime.signscreen.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        // NavBarItems - кастомный лист
        NavBarItems.BarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        // TODO need investigation
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = stringResource(id = navItem.titleResId)
                    )
                },
                label = { Text(text = stringResource(id = navItem.titleResId)) })
        }
    }
}

data class BarItem(
    val titleResId: Int,
    val image: ImageVector,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            titleResId = R.string.profile_nav_bar_title,
            image = Icons.Filled.Home,
            route = Screen.UserPersonalDataScreen.route
        ),
//        BarItem(
//            title = "Contacts",
//            image = Icons.Filled.Face,
//            route = "contacts"
//        ),
        BarItem(
            titleResId = R.string.portfolio_nav_bar_title,
            image = Icons.Filled.Favorite,
            route = Screen.PortfolioScreen.route
        )
    )
}