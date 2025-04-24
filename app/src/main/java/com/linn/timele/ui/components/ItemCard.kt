package com.linn.timele.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linn.timele.ui.ItemDisplay
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(
    item: ItemDisplay,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "No.${item.id} ${item.name}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Days since creation: ${item.daysSinceCreation}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Average price per day: $${
                        String.format(
                            Locale.US,
                            "%.2f",
                            item.averagePricePerDay
                        )
                    }",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        IconButton(
            onClick = onEdit,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_edit),
                contentDescription = "Edit"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    val sampleItem = ItemDisplay(
        id = 1L,
        name = "Sample Item",
        daysSinceCreation = 30L,
        averagePricePerDay = 2.5
    )

    ItemCard(
        item = sampleItem,
        onEdit = {}
    )
} 