package com.example.signinapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// TfLStaffInfoView is a composable that displays a label and a value.
@Preview
@Composable
fun TfLStaffInfoView(
    label : String = "Name: ",
    value: String = "name "
){
    Row (
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){

        Text(
            text = label,
            color = Color.Black,
            fontSize = 21.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = value,
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}