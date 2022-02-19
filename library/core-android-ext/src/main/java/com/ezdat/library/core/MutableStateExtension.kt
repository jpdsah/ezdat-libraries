package com.ezdat.library.core

import androidx.compose.runtime.MutableState

inline fun <T> MutableState<T>.update(onChange: (T) -> T) {
    this.value = onChange(this.value)
}