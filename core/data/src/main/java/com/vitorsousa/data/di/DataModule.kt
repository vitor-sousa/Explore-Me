package com.vitorsousa.data.di

import com.vitorsousa.data.repository.FirestoreTouristSpotRepository
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
        firestoreTouristSpotRepository: FirestoreTouristSpotRepository
    ): TouristSpotRepository

}