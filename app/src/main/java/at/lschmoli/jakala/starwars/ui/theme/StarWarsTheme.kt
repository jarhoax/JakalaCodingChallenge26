package at.lschmoli.jakala.starwars.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkSideColors = darkColorScheme(
    primary = Color(0xFFFF0000),
    background = Color.Black,
    surface = Color(0xFF121212),
    onPrimary = Color.White
)

private val LightSideColors = lightColorScheme(
    primary = Color(0xFFFFD700),
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    onPrimary = Color.Black
)

@Composable
fun StarWarsTheme(content: @Composable () -> Unit) {
    val colors = if (ThemeState.isDarkSide) DarkSideColors else LightSideColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
