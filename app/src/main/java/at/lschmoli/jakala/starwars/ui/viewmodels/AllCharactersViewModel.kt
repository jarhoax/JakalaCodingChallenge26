@file:OptIn(InternalSerializationApi::class)

package at.lschmoli.jakala.starwars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.lschmoli.jakala.starwars.models.Character
import at.lschmoli.jakala.starwars.repositories.DefaultStarWarsRepository
import at.lschmoli.jakala.starwars.repositories.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import javax.inject.Inject

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Character>) : UiState()
    data class Error(val message: String) : UiState()
}

@HiltViewModel
class AllCharactersViewModel @Inject constructor(
    private val repository: StarWarsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                _state.value = UiState.Success(repository.getCharacters())
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "")
            }
        }
    }
}