package com.vitorsousa.domain

import com.vitorsousa.data.repository.TouristSpotRepository
import com.vitorsousa.testing.model.TouristSpotFactory
import com.vitorsousa.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetTouristSpotUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    lateinit var getTouristSpotUseCase: GetTouristSpotUseCase

    @Mock
    lateinit var repository: TouristSpotRepository

    private val touristSpotFake = TouristSpotFactory.createTouristSpotList()

    @Before
    fun setup() {
        getTouristSpotUseCase = GetTouristSpotUseCase(repository)
    }

    @Test
    fun `when getSpots called should return a flow with TouristSpots list`() = runTest {
        `when`(repository.getTopTouristSpots(anyString(), anyInt())).thenReturn(
            flowOf(touristSpotFake)
        )

        val result = getTouristSpotUseCase.invoke(anyInt())

        verify(repository).getTopTouristSpots(anyString(), anyInt())

        assertEquals(touristSpotFake, result.first())
    }

}
