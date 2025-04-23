package com.linn.timele.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linn.timele.AppViewModelProvider
import com.linn.timele.ui.ItemViewModel

@Composable
fun ItemListScreen(
    viewModel: ItemViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onAddItemClick: () -> Unit
) {
    val items by viewModel.items.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddItemClick
            ) {
                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_input_add),
                    contentDescription = "Add Item"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Items",
                style = MaterialTheme.typography.headlineMedium,
                modifier = androidx.compose.ui.Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    com.linn.timele.ui.components.ItemListItem(item = item)
                }
            }
        }
    }
}