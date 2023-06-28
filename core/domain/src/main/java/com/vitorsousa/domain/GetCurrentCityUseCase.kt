package com.vitorsousa.domain

import com.vitorsousa.data.repository.TouristCitiesRepository
import com.vitorsousa.model.data.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentCityUseCase @Inject constructor(
    private val touristCitiesRepository: TouristCitiesRepository
) {
    operator fun invoke(): Flow<City> = touristCitiesRepository.getCurrentCity()
}
