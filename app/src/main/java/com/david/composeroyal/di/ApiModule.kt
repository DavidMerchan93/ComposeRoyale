package com.david.composeroyal.di

import com.david.composeroyal.data.api.CardsApi
import com.david.composeroyal.data.api.TokenApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideCardApi(httpClient: HttpClient): CardsApi {
        return CardsApi(httpClient)
    }

    @Provides
    @Singleton
    fun provideTokenApi(httpClient: HttpClient): TokenApi {
        return TokenApi(httpClient)
    }
}
