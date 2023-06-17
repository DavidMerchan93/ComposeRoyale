package com.david.composeroyal.common

import androidx.lifecycle.SavedStateHandle

fun SavedStateHandle.getString(key: String): String? {
    return get<String>(key)
}
