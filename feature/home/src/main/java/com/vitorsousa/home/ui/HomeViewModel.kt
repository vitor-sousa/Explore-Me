package com.vitorsousa.home.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vitorsousa.home.R
import com.vitorsousa.home.data.City
import com.vitorsousa.home.data.TouristSpot

class HomeViewModel : ViewModel() {

    var state = mutableStateOf<UiState>(UiState.Loading)

    init {
        state.value = UiState.Success(
            city = City(
                name = "Rio de Janeiro",
                imageRes = R.drawable.img_rio_de_janeiro
            ),
            touristSpotList = TouristSpot.SampleList()
        )
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val city: City, val touristSpotList: List<TouristSpot>) : UiState()
    }

}