package com.vitorsousa.domain

import com.vitorsousa.data.repository.FirestoreTouristSpotRepository
import com.vitorsousa.model.data.TouristSpot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTouristSpotUseCase @Inject constructor(
    private val touristSpotRepository: FirestoreTouristSpotRepository
) {
    operator fun invoke(limit: Int): Flow<List<TouristSpot>> =
        touristSpotRepository.getSpots(limit)

}
