package com.linn.timele.ui.components

import com.linn.timele.data.Item
import java.util.Date

data class ItemDetails(
    val id: Long = 0,
    val name: String = "",
    val price: String = "",
    val createDate: Date = Date()
)

fun Item.toItemDetails() = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    createDate = createDate
)

fun ItemDetails.toItem() = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    createDate = createDate
)