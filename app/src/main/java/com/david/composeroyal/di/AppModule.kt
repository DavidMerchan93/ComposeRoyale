package com.david.composeroyal.di

import android.content.Context
import com.david.composeroyal.data.local.PreferenceSettings
import com.david.composeroyal.data.network.ktorHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
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
    fun provideHttpClient(preferenceSettings: PreferenceSettings): HttpClient {
        return ktorHttpClient(preferenceSettings)
    }
}
