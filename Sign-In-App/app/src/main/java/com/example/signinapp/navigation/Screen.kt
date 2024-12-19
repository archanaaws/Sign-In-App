package com.example.signinapp.navigation

// Screen is a sealed class that represents different screens in the app
sealed class Screen(val route: String) {
    data object LoginScreen : Screen("login_screen")
    data object MyTfLScreen : Screen("my_tfl_screen")
}