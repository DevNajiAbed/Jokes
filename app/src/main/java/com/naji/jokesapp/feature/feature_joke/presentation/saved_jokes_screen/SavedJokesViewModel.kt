package com.naji.jokesapp.feature.feature_joke.presentation.saved_jokes_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naji.jokesapp.feature.feature_joke.domain.model.Joke
import com.naji.jokesapp.feature.feature_joke.domain.use_case.JokeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedJokesViewModel @Inject constructor(
    private val jokeUseCases: JokeUseCases
) : ViewModel() {

    var state by mutableStateOf(emptyList<Joke>())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var recentlyDeletedJoke: Joke? = null

    init {
        viewModelScope.launch {
            jokeUseCases.getAllSavedJokesUseCase()
                .collectLatest { jokes ->
                    state = jokes
                }
        }
    }

    fun onEvent(event: SavedJokesEvent) {
        when(event) {
            is SavedJokesEvent.DeleteJoke -> {
                viewModelScope.launch {
                    jokeUseCases.deleteJokeUseCase(event.joke)
                    recentlyDeletedJoke = event.joke
                    _uiEvent.emit(UiEvent.ShowSnackBar("Deleted successfully"))
                }
            }

            SavedJokesEvent.RestoreRecentlyDeletedJoke -> {
                viewModelScope.launch {
                    jokeUseCases.upsertJokeUseCase(recentlyDeletedJoke ?: return@launch)
                    recentlyDeletedJoke = null
                    _uiEvent.emit(UiEvent.ShowSnackBar("Restored successfully"))
                }
            }
        }
    }
}