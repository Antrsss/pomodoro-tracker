package com.example.pomodorotracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pomodorotracker.presentation.navigation.AppNavigation
import com.example.pomodorotracker.ui.theme.PomodoroTrackerTheme

@Composable
fun PomodoroTrackerApp() {
    PomodoroTrackerTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            //val appState = rememberAppState()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                //scaffoldState = appState.scaffoldState
            ) { contentPadding ->
                AppNavigation(modifier = Modifier.padding(contentPadding))
            }
        }
    }
}