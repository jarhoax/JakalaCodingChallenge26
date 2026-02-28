package at.lschmoli.jakala.starwars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.lschmoli.jakala.starwars.ui.screens.AllCharactersScreen
import at.lschmoli.jakala.starwars.ui.screens.CharacterDetailScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.All.route,
        modifier = modifier
    ) {
        composable(Screen.All.route) {
            AllCharactersScreen(
                onCharacterClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            CharacterDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}