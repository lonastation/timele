package com.linn.timele.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linn.timele.data.Item
import com.linn.timele.data.ItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit

data class ItemDisplay(
    val id: Long,
    val name: String,
    val price: String,
    val createDate: String,
    val daysSinceCreation: Long,
    val averagePricePerDay: Double
)

class ItemListViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    private var _listUiState = MutableStateFlow(ListUiState(listOf()))
    val listUiState: StateFlow<ListUiState> = _listUiState

    init {
        viewModelScope.launch {
            itemRepository.getAllItems().collect { items ->
                _listUiState.value = ListUiState(items.map { item ->
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
                        price = item.price.toString(),
                        createDate = item.createDate.toString(),
                        daysSinceCreation = daysSinceCreation,
                        averagePricePerDay = averagePricePerDay
                    )
                })
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

data class ListUiState(val itemList: List<ItemDisplay> = listOf())