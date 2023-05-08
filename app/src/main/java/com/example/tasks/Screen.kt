package com.example.tasks

sealed class Screen(val route : String) {
    object AuthenticationScreen : Screen("Authentication")
}
