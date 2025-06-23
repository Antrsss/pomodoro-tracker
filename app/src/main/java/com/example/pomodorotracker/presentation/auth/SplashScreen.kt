package com.example.pomodorotracker.presentation.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthState
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel

@Composable
fun SplashScreen(
    isAuthenticatedAction: () -> Unit,
    isNotAuthenticatedAction: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authState = authViewModel.authState.collectAsState()

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> isAuthenticatedAction()
            is AuthState.Unauthenticated -> isNotAuthenticatedAction()
            else -> Unit
        }
    }
    //Set image icon
    Text(text = "Loading...", modifier = Modifier.fillMaxSize().padding(20.dp))
}