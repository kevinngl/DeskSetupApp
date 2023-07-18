package com.kevin.desksetupapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{id}") {
        fun createRoute(id: Long) = "detail/$id"
    }
}
