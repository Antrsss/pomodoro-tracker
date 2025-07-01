package com.example.pomodorotracker.presentation.auth

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthState
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel
import com.example.pomodorotracker.presentation.navigation.ScreenRoutes
import kotlinx.coroutines.delay

@Composable
fun EmailConfirmationScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController,
) {
    val email = authViewModel.uiState.value.email
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when(authState) {
            is AuthState.EmailConfirmed -> navController.navigate(ScreenRoutes.EmailConfirmed.route)
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message,
                Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(40.dp)) {
        Text(text = "Confirm your email address", fontSize = 24.sp)
        Text(text = "We sent a confirmation email to:\n $email\nCheck your email and click to the confirmation link to continue.")
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_APP_EMAIL)
                context.startActivity(intent)
            }
        ) {
            Text(text = "Open email app")
        }

        var time by remember { mutableIntStateOf(60) }
        var canResend by remember { mutableStateOf(true) }

        LaunchedEffect(canResend) {
            if (canResend == false) {
                while(time > 0) {
                    delay(1000)
                    time -= 1
                }
                canResend = false
                time = 60
            }
        }

        Button(
            onClick = {
                resendEmailVerification(context, authViewModel)
                canResend = false
            },
            enabled = canResend
        ) {
            Text(text = "Resend verification email")
        }
        Text(text = if (canResend) "Ready to resend" else "You can send verification email again in: $time s")
    }
}

fun resendEmailVerification(context: Context, authViewModel: AuthViewModel) {
    authViewModel.resendEmailVerification()
    val message = "Verification email was sent once more. Please, check your email"
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT).show()
}