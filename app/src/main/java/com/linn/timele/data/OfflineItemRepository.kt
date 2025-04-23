package com.linn.timele.data

import kotlinx.coroutines.flow.Flow

class OfflineItemRepository(private val itemDao: ItemDao) : ItemRepository {
    override fun getAllItems(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItemById(id: Long): Flow<Item?> = itemDao.getItemById(id)

    override suspend fun insertItem(item: Item) = itemDao.insertItem(item)

    override suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)

    override suspend fun updateItem(item: Item) = itemDao.updateItem(item)
}