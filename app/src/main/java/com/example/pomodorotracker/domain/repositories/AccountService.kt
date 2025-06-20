package com.example.pomodorotracker.domain.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AccountService {
    val currentUser: FirebaseUser?

    suspend fun createAccount(email: String, password: String): Result<AuthResult>
    suspend fun authenticate(email: String, password: String): Result<AuthResult>
    suspend fun sendVerificationEmail(email: String): Result<Unit>
    suspend fun sendRecoveryEmail(email: String): Result<Unit>
    suspend fun updateAccount(user: FirebaseUser)
    suspend fun deleteAccount()
    suspend fun signOut()
}