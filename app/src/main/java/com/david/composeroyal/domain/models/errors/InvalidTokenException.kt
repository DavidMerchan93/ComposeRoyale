package com.david.composeroyal.domain.models.errors

data class InvalidTokenException(
    val detail: String,
) : Throwable(detail)
