package com.example.pomodorotracker

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.pomodorotracker.ui.theme.PomodoroTrackerTheme

@Composable
fun PomodoroTrackerApp() {
    PomodoroTrackerTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            /*val appState = rememberAppState()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = appState.scaffoldState
            ) {

            }*/
        }
    }
}