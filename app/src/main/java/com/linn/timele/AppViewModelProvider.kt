package com.linn.timele

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.linn.timele.ui.ItemViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemViewModel(timeLeApplication().container.itemRepository)
        }
    }
}

fun CreationExtras.timeLeApplication(): TimeLeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TimeLeApplication)