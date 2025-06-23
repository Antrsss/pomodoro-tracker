package com.example.pomodorotracker.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pomodorotracker.presentation.auth.components.AuthButton
import com.example.pomodorotracker.presentation.auth.components.AuthOutlinedField
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel
import com.example.pomodorotracker.presentation.navigation.ScreenRoutes

@Composable
fun SignInScreen(
    navController: NavController,
    onBackClicked: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by authViewModel.uiState

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = onBackClicked,
            modifier = Modifier.fillMaxWidth(),
        ) { Text(text = "Back") }

        Text(text = "Welcome back")

        AuthOutlinedField(
            label = "Email",
            value = uiState.email,
            onValueChange = authViewModel::onEmailChange,
            keyboardType = KeyboardType.Email
        )
        AuthOutlinedField(
            label = "Password",
            value = uiState.password,
            onValueChange = authViewModel::onPasswordChange,
            keyboardType = KeyboardType.Password
        )
        Text(
            text = "Forgot password?",
            color = Color.Blue,
            modifier = Modifier.clickable {
                navController.navigate(ScreenRoutes.ForgotPassword.route)
            }
        )
        AuthButton(
            text = "Sign in",
            onClick = {
                authViewModel.signIn(uiState.email, uiState.password)
            }
        )

        Text(text = "Sign in with")
        //add signing in with google

        Row() {
            Text(text = "Don't have an account?")
            Text(
                text = "Sign up",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate(ScreenRoutes.SignUp.route)
                }
            )
        }
    }
}