package com.breaktime.signscreen.navigation

import com.breaktime.signscreen.navigation.AppDestinations.AccountRoute
import com.breaktime.signscreen.navigation.AppDestinations.ContactsRoute
import com.breaktime.signscreen.navigation.AppDestinations.FavoritesRoute
import com.breaktime.signscreen.navigation.AppDestinations.HomeRoute
import com.breaktime.signscreen.navigation.AppDestinations.LoginGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.MainGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.MainScreenRoute
import com.breaktime.signscreen.navigation.AppDestinations.OnBoardingRoute
import com.breaktime.signscreen.navigation.AppDestinations.PortfolioRoute
import com.breaktime.signscreen.navigation.AppDestinations.PersonalDataRoute
import com.breaktime.signscreen.navigation.AppDestinations.RegistrationRout
import com.breaktime.signscreen.navigation.AppDestinations.LoginRoute
import com.breaktime.signscreen.navigation.AppDestinations.UserMainGraphRoute

object AppDestinations {
    const val OnBoardingRoute = "onboarding"
    const val RegistrationRout = "registration"
    const val LoginRoute = "signIn"

    //    const val ProfileRoute = "profile?userId={userId}&userRole={userRole}"
    const val PersonalDataRoute = "profile"
    const val AccountRoute = "account"

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
    object LoginScreen : Screen(LoginRoute)
    object UserPersonalDataScreen : Screen(PersonalDataRoute)
    object UserAccountScreen : Screen(AccountRoute)
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