package com.linn.timele.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linn.timele.data.Item
import com.linn.timele.data.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date

class UpdateItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    
    fun getItem(itemId: Long): Flow<Item?> = itemRepository.getItemById(itemId)

    fun updateItem(itemId: Long, name: String, price: Double, createDate: Date) {
        viewModelScope.launch {
            val updatedItem = Item(
                id = itemId,
                name = name,
                price = price,
                createDate = createDate
            )
            itemRepository.updateItem(updatedItem)
        }
    }

    fun deleteItem(itemId: Long) {
        viewModelScope.launch {
            val itemToDelete = Item(
                id = itemId,
                name = "",
                price = 0.0,
                createDate = Date()
            )
            itemRepository.deleteItem(itemToDelete)
        }
    }
} 