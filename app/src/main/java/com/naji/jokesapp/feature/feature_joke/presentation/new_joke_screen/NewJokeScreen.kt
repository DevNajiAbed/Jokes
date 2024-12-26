package com.naji.jokesapp.feature.feature_joke.presentation.new_joke_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewJokeScreen(
    viewModel: NewJokeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    //region Showing A New Joke
    state.joke?.let { joke ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = joke.setup,
                    fontSize = 24.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = joke.punchline,
                    fontSize = 24.sp,
                    color = Color.Green,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    viewModel.onEvent(NewJokeEvent.ProvideNewJoke)
                }) {
                    Text(text = "Next")
                }
                Button(onClick = {
                    viewModel.onEvent(NewJokeEvent.SaveJoke)
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
    //endregion
    //region Showing Error Message
    if (state.error.isNotBlank()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = state.error,
                color = Color.Red,
                fontSize = 24.sp
            )
        }
    }
    //endregion
    //region Showing Loading Progress Indicator
    if (state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
    //endregion
}