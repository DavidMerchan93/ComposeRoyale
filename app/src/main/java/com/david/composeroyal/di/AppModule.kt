package com.david.composeroyal.di

import android.content.Context
import androidx.room.Room
import com.david.composeroyal.data.local.PreferenceSettings
import com.david.composeroyal.data.local.database.AppDatabase
import com.david.composeroyal.data.network.KtorHttpClient
import com.david.composeroyal.data.network.KtorHttpClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferences(
        @ApplicationContext context: Context,
    ): PreferenceSettings {
        return PreferenceSettings(context)
    }

    @Provides
    @Singleton
    fun provideKtorHttpClient(preferenceSettings: PreferenceSettings): KtorHttpClient {
        return KtorHttpClientImpl(preferenceSettings)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "RoyalDatabase",
    ).build()
}
