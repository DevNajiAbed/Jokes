package com.naji.jokesapp.feature.feature_joke.presentation.saved_jokes_screen

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
}