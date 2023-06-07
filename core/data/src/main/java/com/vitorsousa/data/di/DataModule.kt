package com.vitorsousa.data.di

import com.vitorsousa.data.repository.FirestoreTouristCityDataSource
import com.vitorsousa.data.repository.FirestoreTouristSpotDataSource
import com.vitorsousa.data.repository.TouristCitiesRepository
import com.vitorsousa.data.repository.TouristSpotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsTouristSpot(
        firestoreTouristSpotDataSource: FirestoreTouristSpotDataSource
    ): TouristSpotRepository

    @Binds
    fun bindsTouristCitiesRepository(
        firestoreTouristCityDataSource: FirestoreTouristCityDataSource
    ): TouristCitiesRepository

}
