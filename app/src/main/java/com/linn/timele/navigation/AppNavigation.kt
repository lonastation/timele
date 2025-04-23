package com.linn.timele.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linn.timele.AppViewModelProvider
import com.linn.timele.ui.ItemViewModel
import com.linn.timele.ui.screens.AddItemScreen
import com.linn.timele.ui.screens.ItemListScreen
import com.linn.timele.ui.screens.UpdateItemScreen

sealed class Screen(val route: String) {
    object ItemList : Screen("itemList")
    object AddItem : Screen("addItem")
    object UpdateItem : Screen("update_item/{itemId}") {
        fun createRoute(itemId: String) = "update_item/$itemId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ItemViewModel = viewModel(factory = AppViewModelProvider.Factory)

    NavHost(
        navController = navController,
        startDestination = Screen.ItemList.route
    ) {
        composable(Screen.ItemList.route) {
            ItemListScreen(
                viewModel = viewModel,
                onAddItemClick = {
                    navController.navigate(Screen.AddItem.route)
                },
                navController = navController
            )
        }
        composable(Screen.AddItem.route) {
            AddItemScreen(
                onSave = { name, price, date ->
                    viewModel.addItem(name, price, date)
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.UpdateItem.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: return@composable
            UpdateItemScreen(
                itemId = itemId,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
} 