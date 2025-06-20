package com.example.pomodorotracker.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.pomodorotracker.presentation.auth.SignInScreen
import com.example.pomodorotracker.presentation.auth.SignUpScreen
import com.example.pomodorotracker.presentation.auth.StartScreen

internal fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    composable(route = ScreenRoutes.Start.route) {
        StartScreen(
            navController = navController,
            onCreateAccountClicked = TODO(),
            onSignInClicked = TODO()
        )
    }
    composable(route = ScreenRoutes.SignUp.route) {
        SignUpScreen(
            navController = navController,
            onBackClicked = TODO(),
            onSignUpClicked = TODO(),
            goToSignInScreen = TODO()
        )
    }
    composable(route = ScreenRoutes.SignIn.route) {
        SignInScreen(
            navController = navController,
            onBackClicked = TODO(),
            onSignInClicked = TODO(),
            goToForgotPasswordScreen = TODO(),
            goToCreateAccountScreen = { navController.navigate(ScreenRoutes.SignUp.route) }
        )
    }
}