package com.example.pomodorotracker.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmailConfirmedScreen(
    startBtnAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.padding(40.dp)) {
        Text(text = "Email was successfully confirmed!", fontSize = 20.sp)
        Text(text = "Now you can start to use this app.")
        Button(onClick = startBtnAction) {
            Text("Start")
        }
    }
}