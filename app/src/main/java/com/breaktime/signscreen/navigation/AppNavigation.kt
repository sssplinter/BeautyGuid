package com.breaktime.signscreen.navigation

import com.breaktime.signscreen.navigation.AppDestinations.AccountRoute
import com.breaktime.signscreen.navigation.AppDestinations.ContactsRoute
import com.breaktime.signscreen.navigation.AppDestinations.FavoritesRoute
import com.breaktime.signscreen.navigation.AppDestinations.HomeRoute
import com.breaktime.signscreen.navigation.AppDestinations.LoginGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.LoginRoute
import com.breaktime.signscreen.navigation.AppDestinations.MainGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.MainScreenRoute
import com.breaktime.signscreen.navigation.AppDestinations.OnBoardingRoute
import com.breaktime.signscreen.navigation.AppDestinations.OpenPhotoPreviewRouteWithArguments
import com.breaktime.signscreen.navigation.AppDestinations.PersonalDataRoute
import com.breaktime.signscreen.navigation.AppDestinations.PortfolioRouteWithArguments
import com.breaktime.signscreen.navigation.AppDestinations.RegistrationRout
import com.breaktime.signscreen.navigation.AppDestinations.UserMainGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.UserMastersRoute
import com.breaktime.signscreen.navigation.AppDestinations.UserSalonsRoute

object AppDestinations {
    const val OnBoardingRoute = "onboarding"
    const val RegistrationRout = "registration"
    const val LoginRoute = "signIn"

    const val PersonalDataRoute = "profile"
    const val AccountRoute = "account"

    const val UserSalonsRoute = "userSalons"
    const val UserMastersRoute = "userMasters"

    const val LoginGraphRoute = "login"
    const val UserMainGraphRoute = "userMain"

    const val MainScreenRoute = "mainScreen"
    const val MainGraphRoute = "main"

    const val HomeRoute = "home"
    const val ContactsRoute = "contacts"
    const val FavoritesRoute = "favorites"

    const val OpenPhotoPreviewRouteWithArguments =
        "salonPhoto?salonId={salonId},scrollToId={scrollToId}"
    const val OpenPhotoPreviewRoute = "salonPhoto?"

    const val PortfolioRouteWithArguments = "portfolio?salonId={salonId}"
    const val PortfolioRoute = "portfolio?"
}

sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen(OnBoardingRoute)
    object RegistrationScreen : Screen(RegistrationRout)
    object LoginScreen : Screen(LoginRoute)
    object UserPersonalDataScreen : Screen(PersonalDataRoute)
    object UserAccountScreen : Screen(AccountRoute)
    object PortfolioScreen : Screen(PortfolioRouteWithArguments)
    object MainScreen : Screen(MainScreenRoute)
    object UserMastersScreen : Screen(UserMastersRoute)
    object UserSalonsScreen : Screen(UserSalonsRoute)
    object HomeScreen : Screen(HomeRoute)
    object ContactsScreen : Screen(ContactsRoute)
    object FavoritesScreen : Screen(FavoritesRoute)
    object SalonNewsScreen : Screen(OpenPhotoPreviewRouteWithArguments)
}

sealed class Graph(val route: String) {
    object LoginGraph : Screen(LoginGraphRoute)
    object UserMainGraph : Screen(UserMainGraphRoute)
    object MainScreenGraph : Screen(MainGraphRoute)
}