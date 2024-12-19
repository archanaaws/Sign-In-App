package com.example.signinapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.signinapp.MainActivity
import com.example.signinapp.R
import com.example.signinapp.components.User
import com.example.signinapp.components.TfLAlertDialog
import com.example.signinapp.components.TfLButton
import com.example.signinapp.components.TfLStaffInfoView

/**
 * MyTfLPage composable is the screen that the user sees after they have successfully signed in.
 * It displays the user's name, role, and office.
 * It also contains a button that the user can tap to sign out.
 * If the user taps the sign out button, an alert dialog is shown.
 */
@Composable
fun MyTfLPage(
    navController: NavHostController
) {

    var shouldShowAlertDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    BackHandler {
        (context as? MainActivity)?.finish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp, top = 36.dp, bottom = 36.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.transport_for_london_1_logo_svg_vector),
                contentDescription = stringResource(id = R.string.tfl_logo_content_desc),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(
                    50.dp, 50.dp
                )
            )

            Text(
                text = stringResource(id = R.string.my_tfl),
                color = Color.Black,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )
        }

        TfLStaffInfoView(label = stringResource(id = R.string.name), value = User.name)
        TfLStaffInfoView(label = stringResource(id = R.string.role), value = User.role)
        TfLStaffInfoView(label = stringResource(id = R.string.office), value = User.office)

        Spacer(modifier = Modifier.weight(1f))
        TfLButton(
            text = stringResource(id = R.string.sign_out),
            modifier = Modifier.align(Alignment.End),
            onClick = {
                shouldShowAlertDialog = true
            }
        )

        if (shouldShowAlertDialog) {
            TfLAlertDialog(
                title = stringResource(id = R.string.sign_out_alert_title),
                message = stringResource(id = R.string.sign_out_alert_message),
                dismissButtonText = stringResource(id = R.string.cancel),
                confirmButtonText = stringResource(id = R.string.sign_out),
                onDismissTapped = {
                    shouldShowAlertDialog = false
                },
                onConfirmTapped = {
                    shouldShowAlertDialog = false
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview
@Composable
fun MyTflPagePreview() {
    MyTfLPage(navController = rememberNavController())
}