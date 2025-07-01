package com.example.pomodorotracker.presentation.auth.viewmodels

sealed class AuthState {
    object Loading : AuthState()
    object Unauthenticated : AuthState()
    data class Authenticated(val message: String? = null) : AuthState()
    data class EmailConfirmationNeeded(val message: String? = null) : AuthState()
    object EmailConfirmed : AuthState()
    data class Error(val message: String) : AuthState()

    fun copyWithMessage(message: String?): AuthState {
        return when (this) {
            is Authenticated -> this.copy(message = message)
            else -> this
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
)