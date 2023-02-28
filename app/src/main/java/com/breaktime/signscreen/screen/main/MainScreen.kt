package com.breaktime.signscreen.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.breaktime.signscreen.navigation.Graph
import com.breaktime.signscreen.navigation.Screen
import com.breaktime.signscreen.screen.appointments.specialists.SpecialistsScreen
import com.breaktime.signscreen.screen.portfolio.PortfolioScreen
import com.breaktime.signscreen.screen.profile.personalAccount.PersonalAccount
import com.breaktime.signscreen.screen.profile.personalData.EditProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                NavigationHost(
                    navController = navController
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.MainScreenGraph.route,
    ) {
        mainScreen(navController)
    }
}

fun NavGraphBuilder.mainScreen(navController: NavController) {
    navigation(
        startDestination = Screen.UserPersonalDataScreen.route, route = Graph.MainScreenGraph.route
    ) {
        composable(route = Screen.UserPersonalDataScreen.route) {
            EditProfileScreen(
                onNavigateToPersonalAccount = { navController.navigate(Screen.UserAccountScreen.route) })
        }
        composable(route = Screen.UserAccountScreen.route) {
            PersonalAccount(
                // TODO problem with navigation to login graph
                onSignOut = { navController.navigate(Graph.LoginGraph.route) },
                onEditPersonalData = {
                    navController.navigate(Screen.UserPersonalDataScreen.route)
                },
                onOpenAppointments = {},
                onOpenSalons = {},
                onOpenMasters = {
                    navController.navigate(Screen.UserMastersScreen.route)
                }
            )
        }
        composable(route = Screen.UserMastersScreen.route) {
            SpecialistsScreen(onNavigateBack =
            {
                navController.navigate(Screen.UserAccountScreen.route)
            })
        }
        composable(route = Screen.PortfolioScreen.route) {
            PortfolioScreen(onNavigateBack = {}, onOpenPhoto = {})
        }
    }
}

@Composable
fun TopAppBar() {
    TopAppBar(title = {
        Text(
            "Bottom Navigation Demo",
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
    }, backgroundColor = MaterialTheme.colors.onPrimary)
}

@Composable
fun Contacts() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "contacts",
            tint = Color.Blue,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Home() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "home",
            tint = Color.Blue,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Favorites() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "favorites",
            tint = Color.Blue,
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}
