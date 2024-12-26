package com.naji.jokesapp.feature.feature_joke.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naji.jokesapp.feature.feature_joke.data.data_source.local.dao.JokeDao
import com.naji.jokesapp.feature.feature_joke.domain.model.Joke

@Database(
    entities = [Joke::class],
    version = 1
)
abstract class JokeDB : RoomDatabase() {
    abstract val jokeDao: JokeDao

    companion object {
        const val DB_NAME = "joke_db"
    }
}