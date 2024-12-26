package com.naji.jokesapp.feature.feature_joke.domain.use_case

import com.naji.jokesapp.feature.feature_joke.domain.model.Joke
import com.naji.jokesapp.feature.feature_joke.domain.repository.JokeRepository
import javax.inject.Inject

class UpsertJokeUseCase @Inject constructor(
    private val repository: JokeRepository
) {
    suspend operator fun invoke(joke: Joke) {
        repository.upsertJoke(joke)
    }
}