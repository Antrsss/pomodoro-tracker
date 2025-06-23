package com.example.pomodorotracker.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.pomodorotracker.presentation.auth.ForgotPasswordScreen
import com.example.pomodorotracker.presentation.auth.SignInScreen
import com.example.pomodorotracker.presentation.auth.SignUpScreen
import com.example.pomodorotracker.presentation.auth.StartScreen

fun NavGraphBuilder.authGraph(
    navController: NavController
) {
    composable(route = ScreenRoutes.Start.route) {
        StartScreen(navController = navController)
    }
    composable(route = ScreenRoutes.SignUp.route) {
        SignUpScreen(
            navController = navController,
            onBackClicked = {},
        )
    }
    composable(route = ScreenRoutes.SignIn.route) {
        SignInScreen(
            navController = navController,
            onBackClicked = {},
        )
    }
    composable(route = ScreenRoutes.ForgotPassword.route) {
        ForgotPasswordScreen()
    }
}