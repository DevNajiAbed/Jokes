package com.naji.jokesapp.feature.feature_joke.presentation.new_joke_screen

import com.naji.jokesapp.feature.feature_joke.domain.model.Joke

sealed class NewJokeEvent {
    data object ProvideNewJoke : NewJokeEvent()
    data object SaveJoke : NewJokeEvent()
}