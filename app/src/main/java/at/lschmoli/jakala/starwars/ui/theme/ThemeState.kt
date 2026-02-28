package at.lschmoli.jakala.starwars.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ThemeState {
    var isDarkSide by mutableStateOf(true)
}
