package com.naji.jokesapp.feature.feature_joke.domain.use_case

import com.naji.jokesapp.feature.feature_joke.domain.model.Joke
import com.naji.jokesapp.feature.feature_joke.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSavedJokesUseCase @Inject constructor(
    private val repository: JokeRepository
) {
    operator fun invoke(): Flow<List<Joke>> {
        return repository.getAllSavedJokes()
    }
}