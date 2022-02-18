package com.ezdat.library.core

sealed class Result<out T> {
    data class Success<T>(val data: T, val message: UiText? = null) : Result<T>()
    data class Failure<Nothing>(val error: Error, val reason: UiText? = null) : Result<Nothing>()
}