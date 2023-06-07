package com.vitorsousa.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitorsousa.domain.GetCurrentCityUseCase
import com.vitorsousa.domain.GetTouristSpotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Suppress("MagicNumber")
@HiltViewModel
class HomeViewModel @Inject constructor(
    getTouristSpotUseCase: GetTouristSpotUseCase,
    getCurrentCityUseCase: GetCurrentCityUseCase
) : ViewModel() {

    val headerState: StateFlow<HeaderUiState> =
        getCurrentCityUseCase.invoke()
        .map(HeaderUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HeaderUiState.Loading
            )


    val topTouristSpotFeedState: StateFlow<TopTouristSpotFeedState> =
        getTouristSpotUseCase.invoke("pt", SPOTS_LIMIT)
            .map(TopTouristSpotFeedState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = TopTouristSpotFeedState.Loading
            )


    companion object {
        private const val SPOTS_LIMIT = 10
    }

}
