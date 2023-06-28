package com.vitorsousa.data.repository

import com.vitorsousa.model.R
import com.vitorsousa.model.data.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirestoreTouristCityDataSource @Inject constructor(

): TouristCitiesRepository {
    override fun getCurrentCity(): Flow<City> = flow {
        emit(
            City(
                name = "Rio de Janeiro",
                imageRes = R.drawable.img_rio_de_janeiro
            )
        )
    }

    override fun getCitiesAvailable(): Flow<List<City>> {
        TODO("Not yet implemented")
    }
}
