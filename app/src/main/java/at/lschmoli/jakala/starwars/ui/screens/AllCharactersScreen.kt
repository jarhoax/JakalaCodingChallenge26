package at.lschmoli.jakala.starwars.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import at.lschmoli.jakala.starwars.ui.viewmodels.AllCharactersViewModel
import at.lschmoli.jakala.starwars.ui.viewmodels.UiState

@Composable
fun AllCharactersScreen(
    onCharacterClick: (Int) -> Unit,
    viewModel: AllCharactersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is UiState.Success -> {
            val list = (state as UiState.Success).data

            LazyColumn {
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
