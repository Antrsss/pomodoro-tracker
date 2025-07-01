package com.example.pomodorotracker.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.pomodorotracker.presentation.auth.EmailConfirmationScreen
import com.example.pomodorotracker.presentation.auth.EmailConfirmedScreen
import com.example.pomodorotracker.presentation.auth.ForgotPasswordScreen
import com.example.pomodorotracker.presentation.auth.SignInScreen
import com.example.pomodorotracker.presentation.auth.SignUpScreen
import com.example.pomodorotracker.presentation.auth.StartScreen
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel

fun NavGraphBuilder.authGraph(
    authViewModel: AuthViewModel,
    navController: NavController,
) {
    composable(route = ScreenRoutes.Start.route) {
        StartScreen(
            authViewModel = authViewModel,
            navController = navController)
    }
    composable(route = ScreenRoutes.SignUp.route) {
        SignUpScreen(
            authViewModel = authViewModel,
            navController = navController,
            onBackClicked = { navController.popBackStack() },
        )
    }
    composable(route = ScreenRoutes.SignIn.route) {
        SignInScreen(
            authViewModel = authViewModel,
            navController = navController,
            onBackClicked = { navController.popBackStack() },
        )
    }
    composable(route = ScreenRoutes.ForgotPassword.route) {
        ForgotPasswordScreen(
            authViewModel = authViewModel,
            onBackClicked = { navController.popBackStack() }
        )
    }
    composable(route = ScreenRoutes.EmailConfirmation.route) {
        EmailConfirmationScreen(
            authViewModel = authViewModel,
            navController = navController
        )
    }
    composable(route = ScreenRoutes.EmailConfirmed.route) {
        EmailConfirmedScreen(
            startBtnAction = { navController.navigate(ScreenRoutes.Timer.route) }
        )
    }
}