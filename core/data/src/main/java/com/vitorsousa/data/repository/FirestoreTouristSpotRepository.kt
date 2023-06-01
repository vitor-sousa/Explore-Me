package com.vitorsousa.data.repository

import com.vitorsousa.model.data.TouristSpot
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirestoreTouristSpotRepository @Inject constructor(
) : TouristSpotRepository {

    override fun getSpots(limit: Int): Flow<List<TouristSpot>> = flow {
        emit(TouristSpot.SampleList().take(limit))
    }

}