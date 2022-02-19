package com.ezdat.library.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.*
import kotlin.reflect.KClass

abstract class BaseViewModel : ViewModel() {

    private val userFlowInteraction = Stack<UserInteraction>()

    protected var mappedUserInteractions = hashMapOf<KClass<out UserInteraction>, suspend (UserInteraction) -> Unit>()

    private val _commandObservable = MutableSharedFlow<ViewCommand>()
    val commandObservable: SharedFlow<ViewCommand> = _commandObservable.asSharedFlow()

    protected open fun handleUserInteraction(interaction: UserInteraction) = Unit

    fun interact(userInteraction: UserInteraction) {
        viewModelScope.launch {
            suspendedInteraction(userInteraction)
        }
    }


    protected open fun sendCommand(command: ViewCommand) {
        viewModelScope.launch {
            _commandObservable.emit(command)
        }
    }

    fun reprocessLastInteraction() {
        viewModelScope.launch {
            suspendedReprocessLastInteraction()
        }
    }

    suspend fun suspendedReprocessLastInteraction() {
        internalInteractionHandler(userFlowInteraction.last())
    }

    private suspend fun internalInteractionHandler(interaction: UserInteraction) {
        // It is a mapped function
        if (mappedUserInteractions.containsKey(interaction::class)) {
            mappedUserInteractions[interaction::class]?.invoke(interaction)
            return
        }

        // Handle by using legacy way
        handleUserInteraction(interaction)
    }

    suspend fun suspendedInteraction(userInteraction: UserInteraction) {
        userFlowInteraction.push(userInteraction)
        internalInteractionHandler(userInteraction)
    }

}