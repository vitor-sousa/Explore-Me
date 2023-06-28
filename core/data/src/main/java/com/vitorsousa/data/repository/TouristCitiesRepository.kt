package com.vitorsousa.data.repository

import com.vitorsousa.model.data.City
import kotlinx.coroutines.flow.Flow

interface TouristCitiesRepository {

    fun getCurrentCity(): Flow<City>

    fun getCitiesAvailable(): Flow<List<City>>
}
