package com.naji.jokesapp.feature.feature_joke.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    val setup: String,
    val punchline: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
