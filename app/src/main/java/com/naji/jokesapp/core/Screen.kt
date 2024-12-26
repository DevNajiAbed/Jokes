package com.naji.jokesapp.core

sealed class Screen(val route: String) {
    data object NewJokeScreen : Screen("new_joke_screen")
    data object ViewAllJokesScreen : Screen("view_all_jokes_screen")
}