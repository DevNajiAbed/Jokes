package com.naji.jokesapp.feature.feature_joke.data.data_source.remote

import com.naji.jokesapp.feature.feature_joke.data.data_source.remote.dto.JokeDto
import retrofit2.http.GET

interface OfficialJokeAPI {

    @GET("random_joke")
    suspend fun getRandomJoke(): JokeDto
}