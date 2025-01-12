package com.example.api_marvel.presentation.characters

import androidx.paging.PagingData
import com.example.core.domain.model.Character
import com.example.core.usecase.GetCharactersUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotNull


@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest  {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    // Mock depencies of CharactersViewModel need
    private lateinit var charactersViewModel: CharactersViewModel

    private val pagingDataCharactersFake = PagingData.from(
        listOf(
            Character(
                "3-D Man",
                "https://i.annihil.us/u/prod/marvel/i/mf/c/e0/535fecbbb9784.jpg"
            ),
            Character(
                "3-D Man 1",
                "https://i.annihil.us/u/prod/marvel/i/mf/c/e0/535fecbbb9784.jpg"
            )
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher(scope.testScheduler))
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun should_validate_the_paging_data_object_values_when_calling_charactersPagingData() =
        runTest {
            whenever(getCharactersUseCase.invoke(
                    any()
                )).thenReturn(
                    flowOf(
                        pagingDataCharactersFake
                    )
                )
                // Valida se contem um flow
                val result = charactersViewModel.charactersPagingData("")

                assertNotNull(result.first())

        }
}