package com.naji.jokesapp.feature.feature_joke.presentation.saved_jokes_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.naji.jokesapp.feature.feature_joke.presentation.saved_jokes_screen.components.JokeItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SavedJokesScreen(
    viewModel: SavedJokesViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val hostState = remember { SnackbarHostState() }
    val uiEvent = viewModel.uiEvent

    LaunchedEffect(Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    val result = hostState.showSnackbar(
                        message = event.message,
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(SavedJokesEvent.RestoreRecentlyDeletedJoke)
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state) { joke ->
                JokeItem(
                    joke = joke,
                    onDeleteJoke = {
                        viewModel.onEvent(SavedJokesEvent.DeleteJoke(joke))
                    }
                )
            }
        }
    }
}