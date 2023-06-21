package com.david.composeroyal.di

import com.david.composeroyal.data.local.dao.ArtistDao
import com.david.composeroyal.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideArtistDao(appDatabase: AppDatabase): ArtistDao = appDatabase.artistDao()
}
