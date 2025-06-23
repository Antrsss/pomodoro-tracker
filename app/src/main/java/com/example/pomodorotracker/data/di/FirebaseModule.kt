package com.example.pomodorotracker.data.di

import com.example.pomodorotracker.data.repositories.AccountServiceImpl
import com.example.pomodorotracker.domain.repositories.AccountService
import com.example.pomodorotracker.presentation.auth.viewmodels.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideAccountService(auth: FirebaseAuth): AccountService {
        return AccountServiceImpl(auth)
    }
}