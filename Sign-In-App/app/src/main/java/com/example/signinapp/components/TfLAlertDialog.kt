package com.example.signinapp.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// TfLAlertDialog composable is a custom alert dialog
@Composable
fun TfLAlertDialog(
    title: String,
    message: String,
    dismissButtonText: String?,
    confirmButtonText: String,
    onDismissTapped: () -> Unit,
    onConfirmTapped: () -> Unit
) {

    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        dismissButton = {
            dismissButtonText?.let {
                Button(onClick = {
                    onDismissTapped()
                }) {
                    Text(text = it)
                }
            }
        },
        onDismissRequest = {
            onDismissTapped()
        },
        confirmButton = {
            Button(onClick = {
                onConfirmTapped()
            }) {
                Text(text = confirmButtonText)
            }
        }
    )
}