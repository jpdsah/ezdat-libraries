package com.ezdat.library.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HandleCommand(viewModel: BaseViewModel, block: (command: ViewCommand) -> Unit) {
    LaunchedEffect(key1 = true, block = {
        viewModel.commandObservable.collectLatest { command ->
            block(command)
        }
    })
}