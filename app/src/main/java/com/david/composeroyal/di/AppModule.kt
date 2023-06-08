package com.david.composeroyal.di

import com.david.composeroyal.data.api.CardsApi
import com.david.composeroyal.data.network.ktorHttpClient
import com.david.composeroyal.data.repositories.CardRepositoryImpl
import com.david.composeroyal.domain.repositories.CardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return ktorHttpClient()
    }

    @Provides
    @Singleton
    fun provideCardApi(httpClient: HttpClient): CardsApi {
        return CardsApi(httpClient)
    }

    @Provides
    @Singleton
    fun provideCardsRepository(cardsApi: CardsApi): CardRepository {
        return CardRepositoryImpl(cardsApi)
    }
}
