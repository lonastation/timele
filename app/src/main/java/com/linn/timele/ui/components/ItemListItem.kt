package com.linn.timele.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.linn.timele.ui.ItemDisplay

@Composable
fun ItemListItem(
    item: ItemDisplay,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Days since creation: ${item.daysSinceCreation}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Average price per day: $${String.format("%.2f", item.averagePricePerDay)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
} 