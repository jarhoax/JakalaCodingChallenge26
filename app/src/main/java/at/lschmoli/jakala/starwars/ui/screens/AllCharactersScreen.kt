package at.lschmoli.jakala.starwars.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import at.lschmoli.jakala.starwars.R
import at.lschmoli.jakala.starwars.ui.theme.ThemeState
import at.lschmoli.jakala.starwars.ui.viewmodels.AllCharactersViewModel
import at.lschmoli.jakala.starwars.ui.viewmodels.UiState
import kotlinx.serialization.InternalSerializationApi

@Composable
@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
fun AllCharactersScreen(
    onCharacterClick: (Int) -> Unit,
    viewModel: AllCharactersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Star Wars Characters") },
                actions = {
                    IconButton(
                        onClick = {
                            ThemeState.isDarkSide = !ThemeState.isDarkSide
                        }
                    ) {
                        Icon(
                            imageVector =
                                if (ThemeState.isDarkSide)
                                    ImageVector.vectorResource(R.drawable.light_mode)
                                else
                                    ImageVector.vectorResource(R.drawable.dark_mode),
                            contentDescription =
                                if (ThemeState.isDarkSide)
                                    "Join the light"
                                else
                                    "Join the dark side"
                        )
                    }
                }
            )
        }

    ) { padding ->
        when (state) {
            is UiState.Success -> {
                val list = (state as UiState.Success).data

                LazyColumn(
                    modifier = Modifier.padding(padding)
                ) {
                    itemsIndexed(list) { index, person ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    onCharacterClick(index + 1)
                                }
                        ) {
                            Text(
                                text = person.name,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }

            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> Text("Error")
        }
    }

}
