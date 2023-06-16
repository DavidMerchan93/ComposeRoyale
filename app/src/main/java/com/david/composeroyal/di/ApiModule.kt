package com.david.composeroyal.di

import com.david.composeroyal.data.api.ArtistQueryListApi
import com.david.composeroyal.data.api.CategoriesApi
import com.david.composeroyal.data.api.TokenApi
import com.david.composeroyal.data.network.KtorHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideTokenApi(httpClient: KtorHttpClient): TokenApi {
        return TokenApi(httpClient)
    }

    @Provides
    @Singleton
    fun provideCardApi(httpClient: KtorHttpClient): CategoriesApi {
        return CategoriesApi(httpClient)
    }

    @Provides
    @Singleton
    fun provideArtistQueryListApi(httpClient: KtorHttpClient): ArtistQueryListApi {
        return ArtistQueryListApi(httpClient)
    }
}
