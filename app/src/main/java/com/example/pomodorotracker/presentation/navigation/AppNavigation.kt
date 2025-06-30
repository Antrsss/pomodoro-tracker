package com.example.pomodorotracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pomodorotracker.presentation.auth.SplashScreen
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel
import com.example.pomodorotracker.presentation.timer.TimerScreen
import com.google.android.gms.auth.api.Auth

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = ScreenRoutes.Splash.route) {
        composable(route = ScreenRoutes.Splash.route) {
            SplashScreen(
                isAuthenticatedAction = { navController.navigate(ScreenRoutes.Timer.route) },
                isNotAuthenticatedAction = { navController.navigate(ScreenRoutes.Start.route) },
            )
        }
        authGraph(authViewModel, navController)
        composable(route = ScreenRoutes.Timer.route) {
            TimerScreen()
        }
    }
}