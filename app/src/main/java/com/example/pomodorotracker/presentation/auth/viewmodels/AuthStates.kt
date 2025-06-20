package com.example.pomodorotracker.presentation.auth.viewmodels

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
)