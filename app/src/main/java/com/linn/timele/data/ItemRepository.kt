package com.linn.timele.data


import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getAllItems(): Flow<List<Item>>

    fun getItemById(id: Long): Flow<Item?>

    suspend fun insertItem(item: Item)

    suspend fun updateItem(item: Item)

    suspend fun deleteItem(item: Item)
}