package com.naji.jokesapp.feature.feature_joke.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.naji.jokesapp.feature.feature_joke.domain.model.Joke
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Query("SELECT * FROM joke")
    fun getAllJokes(): Flow<List<Joke>>

    @Upsert
    suspend fun upsertJoke(joke: Joke)

    @Delete
    suspend fun deleteJoke(joke: Joke)
}