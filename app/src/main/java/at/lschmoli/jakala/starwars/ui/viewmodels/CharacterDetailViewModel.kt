package at.lschmoli.jakala.starwars.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
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

@OptIn(InternalSerializationApi::class)
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: StarWarsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["id"])

    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character

    init {
        viewModelScope.launch {
            _character.value = repository.getCharacter(id)
        }
    }
}