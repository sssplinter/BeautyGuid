package com.breaktime.signscreen.navigation

import com.breaktime.signscreen.navigation.AppDestinations.LoginGraphRoute
import com.breaktime.signscreen.navigation.AppDestinations.OnBoardingRoute
import com.breaktime.signscreen.navigation.AppDestinations.PortfolioRoute
import com.breaktime.signscreen.navigation.AppDestinations.ProfileRoute
import com.breaktime.signscreen.navigation.AppDestinations.RegistrationRout
import com.breaktime.signscreen.navigation.AppDestinations.SignInRoute
import com.breaktime.signscreen.navigation.AppDestinations.TestProfile
import com.breaktime.signscreen.navigation.AppDestinations.UserMainGraphRoute

object AppDestinations {
    const val OnBoardingRoute = "onboarding"
    const val RegistrationRout = "registration"
    const val SignInRoute = "signIn"
    const val ProfileRoute = "profile?userId={userId}&userRole={userRole}"
    const val TestProfile = "profile/{userId}"
    const val PortfolioRoute = "portfolio"

    const val LoginGraphRoute = "login"
    const val UserMainGraphRoute = "userMain"
}

sealed class Screen(val route: String) {
    object OnBoardingScreen : Screen(OnBoardingRoute)
    object RegistrationScreen : Screen(RegistrationRout)
    object SignInScreen : Screen(SignInRoute)
    object ProfileScreen : Screen(ProfileRoute)
    object PortfolioScreen : Screen(PortfolioRoute)

    object TestProfileScreen : Screen(TestProfile)
}

sealed class Graph(val route: String) {
    object LoginGraph : Screen(LoginGraphRoute)
    object UserMainGraph : Screen(UserMainGraphRoute)
}