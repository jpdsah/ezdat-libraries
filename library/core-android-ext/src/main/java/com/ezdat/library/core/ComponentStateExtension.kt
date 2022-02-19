package com.ezdat.library.core

import androidx.compose.runtime.MutableState

fun <T> MutableState<ComponentState<T>>.changeToLoadingState() {
    this.value = ComponentState.Loading.FromEmpty
}

fun <T> MutableState<ComponentState<List<T>>>.changeToListLoadingState() {
    when(this.value){
        is ComponentState.Success -> {
            this.value = (this.value as ComponentState.Success<List<T>>).getListLoadingState()
        }
        else ->{
            this.value = ComponentState.Loading.FromEmpty
        }
    }
}

fun <T> MutableState<ComponentState<T>>.changeToSuccessState(successValue: T) {
    this.value = ComponentState.Success(successValue)
}

fun <T> MutableState<ComponentState<T>>.changeToErrorState(error: Error) {
    this.value = ComponentState.Error(error)
}

/**
 * Resolve the LoadingState according the list data.
 */
fun ComponentState<List<*>>.getListLoadingState() : ComponentState.Loading{
    return when(this){
        is ComponentState.Success -> {
            if(data.isEmpty())
                ComponentState.Loading.FromEmpty
            else
                ComponentState.Loading.FromData
        }
        else -> {
            ComponentState.Loading.FromEmpty
        }
    }
}