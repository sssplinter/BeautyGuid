package com.breaktime.signscreen.screen.main

import ProfileScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.breaktime.signscreen.screen.photo.PortfolioScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(topBar = { TopAppBar() },
        content = { NavigationHost(navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) })
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
        startDestination = Screen.PortfolioScreen.route, route = Graph.MainScreenGraph.route
    ) {
        composable(route = Screen.ProfileScreen.route) { backStackEntry ->
            ProfileScreen(backStackEntry.arguments?.getString("userId"),
                backStackEntry.arguments?.getString("userRole"),
                { navController.navigate(Screen.PortfolioScreen.route) })
        }
//        composable(route = Screen.ContactsScreen.route) {
//            Contacts()
//        }
        composable(route = Screen.PortfolioScreen.route) {
            PortfolioScreen()
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
    })
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
