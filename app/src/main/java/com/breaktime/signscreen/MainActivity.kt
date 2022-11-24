package com.breaktime.signscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.breaktime.signscreen.data.entities.UserInfo
import com.breaktime.signscreen.navigation.Graph
import com.breaktime.signscreen.navigation.Screen
import com.breaktime.signscreen.screen.login.OnBoardingScreen
import com.breaktime.signscreen.screen.login.RegistrationScreen
import com.breaktime.signscreen.screen.login.SignInScreen
import com.breaktime.signscreen.screen.main.MainScreen
import com.breaktime.signscreen.ui.theme.SignScreenTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SignScreenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    AppGraph()
                }
            }
        }
    }
}

@Composable
fun AppGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Graph.LoginGraph.route) {
        userMain(navController)
        loginGraph(navController)
    }
}

fun NavGraphBuilder.userMain(navController: NavController) {
    navigation(startDestination = Screen.MainScreen.route, route = Graph.UserMainGraph.route) {
//        composable(route = Screen.ProfileScreen.route) { backStackEntry ->
//            ProfileScreen(
//                backStackEntry.arguments?.getString("userId"),
//                backStackEntry.arguments?.getString("userRole"),
//                { navController.navigate(Screen.PortfolioScreen.route) }
//            )
//        }
//        composable(route = Screen.PortfolioScreen.route) {
//            PhotoScreen()
//        }
        composable(route = Screen.MainScreen.route) {
            MainScreen()
        }
    }
}

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(startDestination = Screen.OnBoardingScreen.route, route = Graph.LoginGraph.route) {
        composable(route = Screen.OnBoardingScreen.route) {
            OnBoardingScreen({ navController.navigate(Screen.SignInScreen.route) },
                { navController.navigate(Screen.RegistrationScreen.route) })
        }
        composable(
            route = Screen.RegistrationScreen.route,
            deepLinks = listOf(navDeepLink { uriPattern = uri }),
        ) {
            RegistrationScreen(onSuccessfullyRegistration = {
                navController.navigate(Graph.UserMainGraph.route) {
                    popUpTo(Screen.OnBoardingScreen.route) { inclusive = true }
                }
            })
        }
        composable(route = Screen.SignInScreen.route) {
            SignInScreen(onSuccessfullyRegistration = {
                navController.navigate(Graph.UserMainGraph.route) {
                    popUpTo(Screen.OnBoardingScreen.route) { inclusive = true }
                }
            })
        }
    }
}

val uri = "https://www.chaitanyamunje.com/userId"

class AssetParamType : NavType<UserInfo>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): UserInfo? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): UserInfo {
        return Gson().fromJson(value, UserInfo::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: UserInfo) {
        bundle.putParcelable(key, value)
    }
}
