package com.naji.jokesapp.feature.feature_joke.presentation.new_joke_screen

import com.naji.jokesapp.feature.feature_joke.domain.model.Joke

data class NewJokeState(
    val isLoading: Boolean = false,
    val joke: Joke? = null,
    val error: String = ""
)