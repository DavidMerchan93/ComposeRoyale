package com.david.composeroyal.di

import com.david.composeroyal.data.api.ArtistApi
import com.david.composeroyal.data.api.ArtistQueryListApi
import com.david.composeroyal.data.api.CategoriesApi
import com.david.composeroyal.data.api.TokenApi
import com.david.composeroyal.data.api.TracksApi
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

    @Provides
    @Singleton
    fun provideArtistApi(httpClient: KtorHttpClient): ArtistApi {
        return ArtistApi(httpClient)
    }

    @Provides
    @Singleton
    fun provideTracksApi(httpClient: KtorHttpClient): TracksApi {
        return TracksApi(httpClient)
    }
}
