package com.linn.timele.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linn.timele.data.Item
import com.linn.timele.data.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit

data class ItemDisplay(
    val id: Long,
    val name: String,
    val daysSinceCreation: Long,
    val averagePricePerDay: Double
)

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {
    private val _items = MutableStateFlow<List<ItemDisplay>>(emptyList())
    val items: StateFlow<List<ItemDisplay>> = _items.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            itemRepository.getAllItems().collect { items ->
                _items.value = items.map { item ->
                    val daysSinceCreation = TimeUnit.MILLISECONDS.toDays(
                        Date().time - item.createDate.time
                    )
                    val averagePricePerDay = if (daysSinceCreation > 0) {
                        item.price / daysSinceCreation
                    } else {
                        item.price
                    }
                    ItemDisplay(
                        id = item.id,
                        name = item.name,
                        daysSinceCreation = daysSinceCreation,
                        averagePricePerDay = averagePricePerDay
                    )
                }
            }
        }
    }

    fun addItem(name: String, price: Double, createDate: Date = Date()) {
        viewModelScope.launch {
            val item = Item(
                name = name,
                price = price,
                createDate = createDate
            )
            itemRepository.insertItem(item)
        }
    }
} 