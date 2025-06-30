package com.example.pomodorotracker.presentation.auth.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorotracker.domain.repositories.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val AUTH_VM = "AUTH_VM"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    var uiState = mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            when {
                accountService.currentUser != null && accountService.isEmailVerified() ->
                    _authState.value = AuthState.Authenticated()
                accountService.currentUser != null && !accountService.isEmailVerified() -> {
                    _authState.value =
                        AuthState.EmailNotVerified("Verification email sent. Please, verify your email")
                }
                else -> _authState.value = AuthState.Unauthenticated
            }
        }
    }

    fun signUp(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            accountService.createAccount(email, password)
                .onSuccess { result ->
                    sendEmailVerification()
                    checkEmailVerification()
                }
                .onFailure { e ->
                    _authState.value = AuthState.Error(e.message ?: "Unknown exception")
                }
        }
    }

    private suspend fun sendEmailVerification() {
        _authState.value = AuthState.Loading
        accountService.sendVerificationEmail()
            .onSuccess {
                _authState.value = AuthState.EmailNotVerified("Verification email was sent. Please, verify your email")
            }
            .onFailure { e ->
                _authState.value = AuthState.Error(e.message ?: "Failed to send verification email")
            }
    }

    fun resendEmailVerification() {
        viewModelScope.launch {
            Log.d(AUTH_VM, authState.value.toString())
            sendEmailVerification()
            Log.d(AUTH_VM, authState.value.toString())
        }
    }

    private fun checkEmailVerification() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                if (accountService.isEmailVerified()) {
                    _authState.value = AuthState.Authenticated()
                    break
                }
            }
        }
    }

    fun signIn(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        viewModelScope.launch {
            accountService.authenticate(email, password)
                .onSuccess {
                    _authState.value = AuthState.Authenticated()
                }
                .onFailure { e ->
                    _authState.value = AuthState.Error(e.message ?: "Unknown exception")
                }
        }
    }

    fun signOut() {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            accountService.signOut()
            _authState.value = AuthState.Unauthenticated
        }
    }
}