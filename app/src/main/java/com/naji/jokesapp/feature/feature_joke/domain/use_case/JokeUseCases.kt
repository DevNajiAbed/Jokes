package com.naji.jokesapp.feature.feature_joke.domain.use_case

data class JokeUseCases(
    val getRandomJokeUseCase: GetRandomJokeUseCase,
    val getAllSavedJokesUseCase: GetAllSavedJokesUseCase,
    val upsertJokeUseCase: UpsertJokeUseCase,
    val deleteJokeUseCase: DeleteJokeUseCase
)
