package com.naji.jokesapp.feature.feature_joke.di

import android.app.Application
import androidx.room.Room
import com.naji.jokesapp.core.Constants
import com.naji.jokesapp.feature.feature_joke.data.data_source.local.JokeDB
import com.naji.jokesapp.feature.feature_joke.data.data_source.local.dao.JokeDao
import com.naji.jokesapp.feature.feature_joke.data.data_source.remote.OfficialJokeAPI
import com.naji.jokesapp.feature.feature_joke.data.repository.JokeRepositoryImpl
import com.naji.jokesapp.feature.feature_joke.domain.repository.JokeRepository
import com.naji.jokesapp.feature.feature_joke.domain.use_case.DeleteJokeUseCase
import com.naji.jokesapp.feature.feature_joke.domain.use_case.GetAllSavedJokesUseCase
import com.naji.jokesapp.feature.feature_joke.domain.use_case.GetRandomJokeUseCase
import com.naji.jokesapp.feature.feature_joke.domain.use_case.JokeUseCases
import com.naji.jokesapp.feature.feature_joke.domain.use_case.UpsertJokeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeatureJokeModule {

    @Provides
    @Singleton
    fun provideOfficialJokeAPI(): OfficialJokeAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build()
            )
            .build()
            .create(OfficialJokeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideJokeDB(app: Application): JokeDB {
        return Room.databaseBuilder(
            app,
            JokeDB::class.java,
            JokeDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideJokeDao(db: JokeDB): JokeDao {
        return db.jokeDao
    }

    @Provides
    @Singleton
    fun provideJokeRepository(
        api: OfficialJokeAPI,
        jokeDao: JokeDao
    ): JokeRepository {
        return JokeRepositoryImpl(api, jokeDao)
    }

    @Provides
    @Singleton
    fun provideJokeUseCases(repository: JokeRepository): JokeUseCases {
        return JokeUseCases(
            getRandomJokeUseCase = GetRandomJokeUseCase(repository),
            getAllSavedJokesUseCase = GetAllSavedJokesUseCase(repository),
            upsertJokeUseCase = UpsertJokeUseCase(repository),
            deleteJokeUseCase = DeleteJokeUseCase(repository)
        )
    }
}