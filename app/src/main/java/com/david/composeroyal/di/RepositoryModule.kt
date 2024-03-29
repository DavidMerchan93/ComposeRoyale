package com.david.composeroyal.di

import com.david.composeroyal.data.api.ArtistApi
import com.david.composeroyal.data.api.ArtistQueryListApi
import com.david.composeroyal.data.api.CategoriesApi
import com.david.composeroyal.data.api.TokenApi
import com.david.composeroyal.data.api.TracksApi
import com.david.composeroyal.data.local.dao.ArtistDao
import com.david.composeroyal.data.repositories.ArtistByQueryRepositoryImpl
import com.david.composeroyal.data.repositories.ArtistLocalRepositoryImpl
import com.david.composeroyal.data.repositories.ArtistRepositoryImpl
import com.david.composeroyal.data.repositories.CategoriesRepositoryImpl
import com.david.composeroyal.data.repositories.TokenRepositoryImpl
import com.david.composeroyal.data.repositories.TracksRepositoryImpl
import com.david.composeroyal.domain.repositories.ArtistByQueryRepository
import com.david.composeroyal.domain.repositories.ArtistLocalRepository
import com.david.composeroyal.domain.repositories.ArtistRepository
import com.david.composeroyal.domain.repositories.CategoriesRepository
import com.david.composeroyal.domain.repositories.TokenRepository
import com.david.composeroyal.domain.repositories.TracksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTokenRepository(
        tokenApi: TokenApi,
    ): TokenRepository {
        return TokenRepositoryImpl(tokenApi)
    }

    @Provides
    @Singleton
    fun provideCardsRepository(categoriesApi: CategoriesApi): CategoriesRepository {
        return CategoriesRepositoryImpl(categoriesApi)
    }

    @Provides
    @Singleton
    fun provideArtistByQueryRepository(artistQueryListApi: ArtistQueryListApi): ArtistByQueryRepository {
        return ArtistByQueryRepositoryImpl(artistQueryListApi)
    }

    @Provides
    @Singleton
    fun provideArtistRepository(artistApi: ArtistApi): ArtistRepository {
        return ArtistRepositoryImpl(artistApi)
    }

    @Provides
    @Singleton
    fun provideTracksRepository(tracksApi: TracksApi): TracksRepository {
        return TracksRepositoryImpl(tracksApi)
    }

    @Provides
    @Singleton
    fun provideArtistLocalRepository(artistDao: ArtistDao): ArtistLocalRepository {
        return ArtistLocalRepositoryImpl(artistDao)
    }
}
