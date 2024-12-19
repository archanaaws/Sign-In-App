package com.example.signinapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// TfLButton is a composable that displays a button with a rounded corner shape.
@Preview
@Composable
fun TfLButton(
    modifier: Modifier = Modifier,
    text: String = "Text",
    onClick: () -> Unit = {}
){
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        )
    }
}