package com.naji.jokesapp.feature.feature_joke.domain.use_case

import com.naji.jokesapp.core.Resource
import com.naji.jokesapp.feature.feature_joke.domain.model.Joke
import com.naji.jokesapp.feature.feature_joke.domain.repository.JokeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetRandomJokeUseCase @Inject constructor(
    private val repository: JokeRepository
) {
    operator fun invoke(): Flow<Resource<Joke>> {
        return flow {
            try {
                emit(Resource.Loading())
                val joke = repository.getRandomJoke()
                emit(Resource.Success(joke))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.localizedMessage ?: "Can't load jokes"))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
            }
        }
    }
}