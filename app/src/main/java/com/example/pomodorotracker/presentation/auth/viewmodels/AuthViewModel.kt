package com.example.pomodorotracker.presentation.auth.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorotracker.data.repositories.AccountServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val accountService: AccountServiceImpl
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

    fun checkAuthStatus() {
        if (accountService.currentUser != null) {
            _authState.value = AuthState.Authenticated
        } else {
            _authState.value = AuthState.Unauthenticated
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
                    _authState.value = AuthState.Authenticated
                }
                .onFailure { e ->
                    _authState.value = AuthState.Error(e.message ?: "Unknown exception")
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
                    _authState.value = AuthState.Authenticated
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