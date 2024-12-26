package com.naji.jokesapp.feature.feature_joke.presentation.saved_jokes_screen

import com.naji.jokesapp.feature.feature_joke.domain.model.Joke

sealed class SavedJokesEvent {
    data class DeleteJoke(val joke: Joke) : SavedJokesEvent()
    data object RestoreRecentlyDeletedJoke : SavedJokesEvent()
}