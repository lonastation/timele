package com.linn.timele

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.linn.timele.ui.ItemListViewModel
import com.linn.timele.ui.UpdateItemViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemListViewModel(timeLeApplication().container.itemRepository)
        }
        initializer {
            UpdateItemViewModel(
                this.createSavedStateHandle(),
                timeLeApplication().container.itemRepository)
        }
    }
}

fun CreationExtras.timeLeApplication(): TimeLeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TimeLeApplication)