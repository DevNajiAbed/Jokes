package com.naji.jokesapp.feature.feature_joke.presentation.new_joke_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naji.jokesapp.core.Resource
import com.naji.jokesapp.feature.feature_joke.domain.use_case.JokeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewJokeViewModel @Inject constructor(
    private val jokeUseCases: JokeUseCases
) : ViewModel() {

    var state by mutableStateOf(NewJokeState())
        private set

    init {
        onEvent(NewJokeEvent.ProvideNewJoke)
    }

    fun onEvent(event: NewJokeEvent) {
        when (event) {
            NewJokeEvent.ProvideNewJoke -> {
                viewModelScope.launch {
                    jokeUseCases.getRandomJokeUseCase()
                        .collectLatest { value ->
                            when (value) {
                                is Resource.Error -> {
                                    state = NewJokeState(
                                        error = value.message ?: return@collectLatest
                                    )
                                }

                                is Resource.Success -> {
                                    state = NewJokeState(
                                        joke = value.data
                                    )
                                }

                                is Resource.Loading -> {
                                    state = NewJokeState(
                                        isLoading = true
                                    )
                                }
                            }
                        }
                }
            }

            NewJokeEvent.SaveJoke -> {
                viewModelScope.launch {
                    state.joke?.let {
                        jokeUseCases.upsertJokeUseCase(it)
                    }
                }
            }
        }
    }
}