package com.naji.jokesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.naji.jokesapp.feature.feature_joke.presentation.new_joke_screen.NewJokeScreen
import com.naji.jokesapp.feature.feature_joke.presentation.saved_jokes_screen.SavedJokesScreen
import com.naji.jokesapp.ui.theme.JokesAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokesAppTheme {
                val navigationItems = listOf(
                    NavigationItem(0, "Home", Icons.Default.Home),
                    NavigationItem(1, "Saved jokes", Icons.Default.Favorite)
                )
                var selectedState by remember { mutableIntStateOf(navigationItems[0].index) }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            navigationItems,
                            selectedState = selectedState,
                            onItemClick = { index ->
                                selectedState = index
                            }
                        )
                    }
                )  {
                    Box(modifier = Modifier.fillMaxSize()) {
                        when(selectedState) {
                            0 -> NewJokeScreen()
                            1 -> SavedJokesScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navigationItems: List<NavigationItem>,
    selectedState: Int,
    onItemClick: (Int) -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedState == index,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}

data class NavigationItem(
    val index: Int,
    val label: String,
    val icon: ImageVector
)