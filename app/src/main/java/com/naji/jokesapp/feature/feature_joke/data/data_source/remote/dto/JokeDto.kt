package com.naji.jokesapp.feature.feature_joke.data.data_source.remote.dto

import com.naji.jokesapp.feature.feature_joke.domain.model.Joke

data class JokeDto(
    val id: Int,
    val punchline: String,
    val setup: String,
    val type: String
) {
    fun toJoke(): Joke {
        return Joke(
            setup = setup,
            punchline = punchline
        )
    }
}