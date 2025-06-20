package com.example.pomodorotracker.presentation.auth.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AuthOutlinedField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}