package com.vitorsousa.domain

import com.vitorsousa.data.repository.TouristSpotRepository
import com.vitorsousa.model.data.TouristSpot
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTouristSpotUseCase @Inject constructor(
    private val touristSpotRepository: TouristSpotRepository
) {
    operator fun invoke(language: String, limit: Int): Flow<List<TouristSpot>> =
        touristSpotRepository.getTopTouristSpots(language, limit)


}
