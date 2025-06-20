package com.example.pomodorotracker.data.di

import com.example.pomodorotracker.data.repositories.AccountServiceImpl
import com.example.pomodorotracker.domain.repositories.AccountService
import com.google.firebase.auth.FirebaseAuth
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
    fun provideAuthRepository(auth: FirebaseAuth): AccountService {
        return AccountServiceImpl(auth)
    }
}