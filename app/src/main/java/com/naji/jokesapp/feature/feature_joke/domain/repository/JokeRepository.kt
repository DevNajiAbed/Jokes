package com.naji.jokesapp.feature.feature_joke.domain.repository

import com.naji.jokesapp.feature.feature_joke.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    suspend fun getRandomJoke(): Joke

    fun getAllSavedJokes(): Flow<List<Joke>>

    suspend fun upsertJoke(joke: Joke)

    suspend fun deleteJoke(joke: Joke)
}

// data -> domain <- presentation