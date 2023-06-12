package com.david.composeroyal.di

import com.david.composeroyal.data.api.CardsApi
import com.david.composeroyal.data.api.TokenApi
import com.david.composeroyal.data.repositories.CardRepositoryImpl
import com.david.composeroyal.data.repositories.TokenRepositoryImpl
import com.david.composeroyal.domain.repositories.CardRepository
import com.david.composeroyal.domain.repositories.TokenRepository
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
    fun provideCardsRepository(cardsApi: CardsApi): CardRepository {
        return CardRepositoryImpl(cardsApi)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(
        tokenApi: TokenApi,
    ): TokenRepository {
        return TokenRepositoryImpl(tokenApi)
    }
}
