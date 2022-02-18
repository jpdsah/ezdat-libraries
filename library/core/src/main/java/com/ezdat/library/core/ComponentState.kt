package com.ezdat.library.core

sealed class ComponentState<out T> {
    object Initializing : ComponentState<Nothing>()
    sealed class Loading : ComponentState<Nothing>() {
        object FromEmpty : Loading()
        object FromData : Loading()
    }

    data class Error(val error: com.ezdat.library.core.Error) : ComponentState<Nothing>()
    data class Success<T>(val data: T) : ComponentState<T>()
}
