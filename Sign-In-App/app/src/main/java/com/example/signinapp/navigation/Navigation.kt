package com.example.signinapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.signinapp.ui.screens.LoginPage
import com.example.signinapp.ui.screens.MyTfLPage

// Navigation composable is used to navigate between different screens
@Composable
fun Navigation(
    navController: NavHostController
) {

    // Create a NavHost with the navController and the start destination
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        // Add composable screens
        composable(Screen.LoginScreen.route) {
            LoginPage(navController)
        }
        composable(Screen.MyTfLScreen.route) {
            MyTfLPage(navController)
        }
    }
}