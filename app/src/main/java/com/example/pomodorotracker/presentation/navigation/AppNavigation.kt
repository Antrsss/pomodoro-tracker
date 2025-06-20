package com.example.pomodorotracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pomodorotracker.presentation.auth.SplashScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenRoutes.Splash.route) {
        composable(route = ScreenRoutes.Splash.route) {
            SplashScreen(navController)
        }
        authGraph(navController)
    }
}