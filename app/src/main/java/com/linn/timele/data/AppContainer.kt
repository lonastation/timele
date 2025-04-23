package com.linn.timele.data

import android.content.Context

interface AppContainer {
    val itemRepository: ItemRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemRepository: ItemRepository by lazy {
        OfflineItemRepository(AppDatabase.getDatabase(context).itemDao())
    }
}