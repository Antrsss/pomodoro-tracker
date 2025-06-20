package com.example.pomodorotracker.presentation.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthState
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel
import com.example.pomodorotracker.presentation.navigation.ScreenRoutes

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authState = authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when(authState) {
            is AuthState.Unauthenticated -> navController.navigate(ScreenRoutes.Start.route)
            is AuthState.Authenticated -> navController.navigate(ScreenRoutes.Timer.route)
            else -> Unit
        }
    }
    //Set image icon
    Text(text = "Loading...", modifier = Modifier.fillMaxSize())
}