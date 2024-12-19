package com.example.signinapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.signinapp.R
import com.example.signinapp.components.User
import com.example.signinapp.components.TfLAlertDialog
import com.example.signinapp.navigation.Screen
import com.example.signinapp.components.TfLButton

/**
 * LoginPage composable is the first screen that the user sees when they open the app.
 * It contains two text fields for the user to enter their TfL ID and password.
 * It also contains a button that the user can tap to sign in.
 * If the user enters incorrect TfL ID or password, an alert dialog is shown.
 */
@Composable
fun LoginPage(
    navController: NavHostController
) {

    var tflId by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    var shouldShowAlertDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            painter = painterResource(id = R.drawable.transport_for_london_1_logo_svg_vector),
            contentDescription = stringResource(id = R.string.tfl_logo_content_desc),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(120.dp, 120.dp)
        )

        Text(
            text = stringResource(id = R.string.tfl_id),
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )

        TextField(
            value = tflId,
            onValueChange = {
                tflId = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.tfl_id_placeholder))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                errorPlaceholderColor = Color.LightGray,
                disabledPlaceholderColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .testTag(ID_TEXT_FIELD_TAG)
        )

        Text(
            text = stringResource(id = R.string.password),
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.password_placeholder))
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray,
                errorPlaceholderColor = Color.LightGray,
                disabledPlaceholderColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            ),
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .testTag(PW_TEXT_FIELD_TAG)

        )

        TfLButton(
            text = stringResource(id = R.string.sign_in),
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                if (canSignInUser(tflId, password)) {
                    // Navigate to the My TfL screen
                    navController.navigate(Screen.MyTfLScreen.route)

                    // Reset the TfL ID and password fields
                    tflId = ""
                    password = ""
                } else {
                    // Show an alert dialog
                    shouldShowAlertDialog = true
                }
            }
        )

        if (shouldShowAlertDialog) {
            TfLAlertDialog(
                title = stringResource(id = R.string.login_error_title),
                message = stringResource(id = R.string.login_error_message),
                dismissButtonText = null,
                confirmButtonText = stringResource(id = R.string.done),
                onDismissTapped = {
                    shouldShowAlertDialog = false
                },
                onConfirmTapped = {
                    shouldShowAlertDialog = false
                }
            )
        }
    }
}

// Function to check if the user can sign in
// Checks if the TfL ID and password entered by the user are matching the dummy User data
fun canSignInUser(tflId: String, password: String): Boolean {
    return tflId == User.tflID && password == User.password
}

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage(navController = rememberNavController())
}

const val ID_TEXT_FIELD_TAG = "idTextFieldTag"
const val PW_TEXT_FIELD_TAG = "passwordTextFieldTag"