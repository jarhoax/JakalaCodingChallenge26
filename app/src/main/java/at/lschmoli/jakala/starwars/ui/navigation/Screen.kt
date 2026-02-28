package at.lschmoli.jakala.starwars.ui.navigation

sealed class Screen(val route: String) {

    data object All : Screen("all")

    data object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}