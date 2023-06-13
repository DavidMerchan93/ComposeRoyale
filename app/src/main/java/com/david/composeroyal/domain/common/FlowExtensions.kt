package com.david.composeroyal.domain.common

import com.david.composeroyal.data.api.common.Constants
import com.david.composeroyal.data.models.InvalidTokenException
import com.david.composeroyal.domain.useCase.UpdateAccessTokenUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.retryWhen

fun <T> Flow<T>.retryUpdateToken(tokenUseCase: UpdateAccessTokenUseCase): Flow<T> {
    return this.retryWhen { exception, count ->
        if (count > Constants.MAX_RETRIES) {
            false
        } else if (exception is InvalidTokenException) {
            tokenUseCase().collect()
            delay(Constants.DELAY_TIME)
            true
        } else {
            throw exception
        }
    }
}
