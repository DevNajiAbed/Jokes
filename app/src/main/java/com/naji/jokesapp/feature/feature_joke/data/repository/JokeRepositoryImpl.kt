package com.naji.jokesapp.feature.feature_joke.data.repository

import com.naji.jokesapp.feature.feature_joke.data.data_source.local.dao.JokeDao
import com.naji.jokesapp.feature.feature_joke.data.data_source.remote.OfficialJokeAPI
import com.naji.jokesapp.feature.feature_joke.domain.model.Joke
import com.naji.jokesapp.feature.feature_joke.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val api: OfficialJokeAPI,
    private val jokeDao: JokeDao
) : JokeRepository {
    override suspend fun getRandomJoke(): Joke {
        return api.getRandomJoke().toJoke()
    }

    override fun getAllSavedJokes(): Flow<List<Joke>> {
        return jokeDao.getAllJokes()
    }

    override suspend fun upsertJoke(joke: Joke) {
        jokeDao.upsertJoke(joke)
    }

    override suspend fun deleteJoke(joke: Joke) {
        jokeDao.deleteJoke(joke)
    }
}