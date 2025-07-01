package com.example.pomodorotracker.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pomodorotracker.presentation.auth.components.AuthButton
import com.example.pomodorotracker.presentation.auth.components.AuthOutlinedField
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthState
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel
import com.example.pomodorotracker.presentation.navigation.ScreenRoutes

@Composable
fun SignUpScreen(
    onBackClicked: () -> Unit,
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val uiState by authViewModel.uiState
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when(authState) {
            is AuthState.EmailConfirmationNeeded -> navController.navigate(ScreenRoutes.EmailConfirmation.route)
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(40.dp)) {
        Button(
            onClick = onBackClicked,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Back")
        }
        Text(text = "Get Started")
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
        AuthButton(
            text = "Sign up",
            onClick = {
                authViewModel.signUp(uiState.email, uiState.password)
            }
        )

        Text(text = "Sign up with")
        //add signing up with google

        Row() {
            Text(text = "Already have en account?")
            Text(
                text = "Sign in",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate(ScreenRoutes.SignIn.route)
            })
        }
    }
}