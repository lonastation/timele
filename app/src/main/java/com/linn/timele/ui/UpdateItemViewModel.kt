package com.linn.timele.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linn.timele.data.ItemRepository
import com.linn.timele.navigation.ItemDetailsDestination
import com.linn.timele.ui.components.ItemDetails
import com.linn.timele.ui.components.toItem
import com.linn.timele.ui.components.toItemDetails
import kotlinx.coroutines.launch

class UpdateItemViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemRepository: ItemRepository
) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Long = checkNotNull(savedStateHandle[ItemDetailsDestination.ITEM_ID_ARG])

    init {
        viewModelScope.launch {
            itemRepository.getItemById(itemId).collect { item ->
                item?.let {
                    itemUiState = itemUiState.copy(
                        itemDetails = it.toItemDetails()
                    )
                }
            }
        }
    }

    fun updateItemUiState(itemDetails: ItemDetails) {
        itemUiState = ItemUiState(itemDetails = itemDetails)
    }


    suspend fun updateItem() {
        itemRepository.updateItem(itemUiState.itemDetails.toItem())
    }

    suspend fun deleteItem() {
        itemRepository.deleteItem(item = itemUiState.itemDetails.toItem())
    }
}

data class ItemUiState(val itemDetails: ItemDetails = ItemDetails())

