package com.example.pomodorotracker.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthState
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel
import com.example.pomodorotracker.presentation.navigation.ScreenRoutes

@Composable
fun StartScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when(authState) {
            is AuthState.Authenticated -> navController.navigate(ScreenRoutes.Timer.route)
            is AuthState.EmailConfirmationNeeded -> navController.navigate(ScreenRoutes.EmailConfirmation.route)
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate(ScreenRoutes.SignIn.route) }
        ) {
            Text(text = "Sign in")
        }
        Button(
            onClick = { navController.navigate(ScreenRoutes.SignUp.route) }
        ) {
            Text(text = "Create an account")
        }
    }
}