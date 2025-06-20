package com.example.pomodorotracker.presentation.navigation

sealed class ScreenRoutes(val route: String) {
    object Splash: ScreenRoutes("splash")

    object Start: ScreenRoutes("start")
    object SignUp: ScreenRoutes("sign_up")
    object SignIn: ScreenRoutes("sign_in")
    object ForgotPassword: ScreenRoutes("forgot_password")

    object Timer: ScreenRoutes("timer")
}