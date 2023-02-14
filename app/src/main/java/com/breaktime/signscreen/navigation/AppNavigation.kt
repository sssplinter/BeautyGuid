package com.breaktime.signscreen.navigation

import com.breaktime.signscreen.navigation.AppDestinations.ContactsRoute
import com.breaktime.signscreen.navigation.AppDestinations.FavoritesRoute
import com.breaktime.signscreen.navigation.AppDestinations.HomeRoute
import com.breaktime.signscreen.navigation.AppDestinations.LoginGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.MainGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.MainScreenRoute
import com.breaktime.signscreen.navigation.AppDestinations.OnBoardingRoute
import com.breaktime.signscreen.navigation.AppDestinations.PortfolioRoute
import com.breaktime.signscreen.navigation.AppDestinations.ProfileRoute
import com.breaktime.signscreen.navigation.AppDestinations.RegistrationRout
import com.breaktime.signscreen.navigation.AppDestinations.SignInRoute
import com.breaktime.signscreen.navigation.AppDestinations.UserMainGraphRoute

object AppDestinations {
    const val OnBoardingRoute = "onboarding"
    const val RegistrationRout = "registration"
    const val SignInRoute = "signIn"
//    const val ProfileRoute = "profile?userId={userId}&userRole={userRole}"
    const val ProfileRoute = "profile"
    const val PortfolioRoute = "portfolio"

    const val LoginGraphRoute = "login"
    const val UserMainGraphRoute = "userMain"

    const val MainScreenRoute = "mainScreen"
    const val MainGraphRoute = "main"

    const val HomeRoute = "home"
    const val ContactsRoute = "contacts"
    const val FavoritesRoute = "favorites"
}

sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen(OnBoardingRoute)
    object RegistrationScreen : Screen(RegistrationRout)
    object SignInScreen : Screen(SignInRoute)
    object ProfileScreen : Screen(ProfileRoute)
    object PortfolioScreen : Screen(PortfolioRoute)
    object MainScreen : Screen(MainScreenRoute)
    object HomeScreen : Screen(HomeRoute)
    object ContactsScreen : Screen(ContactsRoute)
    object FavoritesScreen : Screen(FavoritesRoute)
}

sealed class Graph(val route: String) {
    object LoginGraph : Screen(LoginGraphRoute)
    object UserMainGraph : Screen(UserMainGraphRoute)
    object MainScreenGraph : Screen(MainGraphRoute)
}