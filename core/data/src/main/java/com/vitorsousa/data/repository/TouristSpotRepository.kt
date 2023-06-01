package com.vitorsousa.data.repository

import com.vitorsousa.model.data.TouristSpot
import kotlinx.coroutines.flow.Flow

interface TouristSpotRepository {
    fun getSpots(limit: Int) : Flow<List<TouristSpot>>
}
