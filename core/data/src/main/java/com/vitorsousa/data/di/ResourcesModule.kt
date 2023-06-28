package com.vitorsousa.data.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
class ResourcesModule {

    @Provides
    fun providesResources(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    fun providesLocale(): Locale = Locale.getDefault()

}
