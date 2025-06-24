package com.example.pomodorotracker.presentation.auth

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ForgotPasswordScreen(
    onBackClicked: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(40.dp)) {
        Button(
            onClick = onBackClicked,
            modifier = Modifier.fillMaxWidth(),
        ) { Text(text = "Back") }
        Text(text = "Forgot password", Modifier.fillMaxSize())
    }
}