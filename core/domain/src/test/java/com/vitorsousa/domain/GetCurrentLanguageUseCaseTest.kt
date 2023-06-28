package com.vitorsousa.domain

import com.vitorsousa.domain.GetCurrentLanguageUseCase.Companion.EN
import com.vitorsousa.domain.GetCurrentLanguageUseCase.Companion.PT
import com.vitorsousa.testing.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.Locale

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetCurrentLanguageUseCaseTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getCurrentLanguageUseCase: GetCurrentLanguageUseCase

    @Mock
    lateinit var locale: Locale

    @Before
    fun setUp() {
        getCurrentLanguageUseCase = GetCurrentLanguageUseCase(locale)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when locale language is PT then should return PT`() = runTest {
        `when`(locale.language).thenReturn(PT)

        val result = getCurrentLanguageUseCase.invoke()

        Assert.assertEquals(PT, result)
    }

    @Test
    fun `when locale language is EN then should return EN`() = runTest {
        `when`(locale.language).thenReturn(EN)

        val result = getCurrentLanguageUseCase.invoke()

        Assert.assertEquals(EN, result)
    }

    @Test
    fun `when locale language is not PT or EN then should return EN`() = runTest {
        `when`(locale.language).thenReturn("fr")

        val result = getCurrentLanguageUseCase.invoke()

        Assert.assertEquals(EN, result)
    }
}
