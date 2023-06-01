package com.vitorsousa.home.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitorsousa.domain.GetTouristSpotUseCase
import com.vitorsousa.model.R
import com.vitorsousa.model.data.City
import com.vitorsousa.model.data.TouristSpot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Suppress("MagicNumber")
@HiltViewModel
class HomeViewModel @Inject constructor(
    getTouristSpotUseCase: GetTouristSpotUseCase
) : ViewModel() {

    var state = mutableStateOf<UiState>(UiState.Loading)
        private set

    init {
        state.value = UiState.Success(
            city = City(
                name = "Rio de Janeiro",
                imageRes = R.drawable.img_rio_de_janeiro
            ),
            touristSpotList = getTouristSpotUseCase.invoke(SPOTS_LIMIT)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = emptyList()
                )
        )
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val city: City, val touristSpotList: StateFlow<List<TouristSpot>>) : UiState()
    }

    companion object {
        private const val SPOTS_LIMIT = 10
    }

}
