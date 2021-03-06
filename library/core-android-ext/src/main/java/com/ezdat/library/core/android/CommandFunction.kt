package com.ezdat.library.core.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ezdat.library.core.ViewCommand

/**
 * It is called when you wanna observe a single event and then stop to observing it.
 */
fun autoDisposeCommandObserver(
    lifecycle: LifecycleOwner,
    viewModel: BaseViewModel,
    block: (result: ViewCommand) -> Unit
) {
    val key = lifecycle::class.java.simpleName
    val commandObservable = ((viewModel.commandObservable) as SingleLiveEvent<ViewCommand>)
    commandObservable.observe(
        key,
        lifecycle,
        Observer {
            block(it)
            commandObservable.removeObserver(key)
        }
    )
}

/**
 * It is called when you wanna observe all commands while the lifecycle owner is activated.
 */
fun commandObserver(lifecycle: LifecycleOwner, viewModel: BaseViewModel, block: (result: ViewCommand) -> Unit) {
    ((viewModel.commandObservable) as SingleLiveEvent<ViewCommand>).observe(
        lifecycle::class.java.simpleName,
        lifecycle,
        Observer {
            block(it)
        }
    )
}