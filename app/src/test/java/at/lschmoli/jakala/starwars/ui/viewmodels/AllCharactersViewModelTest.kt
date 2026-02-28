package at.lschmoli.jakala.starwars.ui.viewmodels

import app.cash.turbine.test
import at.lschmoli.jakala.starwars.fakes.FailingStarWarsRepository
import at.lschmoli.jakala.starwars.fakes.FakeStarWarsRepository
import at.lschmoli.jakala.starwars.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

@OptIn(ExperimentalCoroutinesApi::class)
class AllCharactersViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: AllCharactersViewModel
    private val repository = FakeStarWarsRepository()

    @Test
    fun `state flow emits success once repository returns data`() = runTest {
        viewModel = AllCharactersViewModel(repository)

        viewModel.state.test {
            expectThat(awaitItem()).isA<UiState.Loading>()
            advanceUntilIdle()

            val nextState = awaitItem()
            expectThat(nextState).isA<UiState.Success>()

            val data = (nextState as UiState.Success).data
            expectThat(data.first().name) isEqualTo "Luke Skywalker"

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `state flow emits error if repository throws`() = runTest {
        viewModel = AllCharactersViewModel(repository = FailingStarWarsRepository())

        viewModel.state.test {
            expectThat(awaitItem()).isA<UiState.Loading>()
            advanceUntilIdle()

            val nextState = awaitItem()
            expectThat(nextState).isA<UiState.Error>()
        }
    }
}