package com.linn.timele.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.linn.timele.AppViewModelProvider
import com.linn.timele.R
import com.linn.timele.ui.ItemListViewModel
import com.linn.timele.ui.UpdateItemViewModel
import com.linn.timele.ui.screens.AddItemScreen
import com.linn.timele.ui.screens.ItemListScreen
import com.linn.timele.ui.screens.UpdateItemScreen

sealed class Screen(val route: String) {
    object ItemList : Screen("itemList")
    object AddItem : Screen("addItem")
}

interface NavigationDestination {
    val route: String
    val titleRes: Int
}

object ItemDetailsDestination : NavigationDestination {
    override val route = "update_item"
    override val titleRes = R.string.update_item_title
    const val ITEM_ID_ARG = "itemId"
    val routeWithArgs = "${route}/{${ITEM_ID_ARG}}"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val itemListViewModel: ItemListViewModel = viewModel(factory = AppViewModelProvider.Factory)

    NavHost(
        navController = navController,
        startDestination = Screen.ItemList.route
    ) {
        composable(Screen.ItemList.route) {
            ItemListScreen(
                onAddItemClick = {
                    navController.navigate(Screen.AddItem.route)
                },
                navController = navController
            )
        }
        composable(Screen.AddItem.route) {
            AddItemScreen(
                onSave = { name, price, date ->
                    itemListViewModel.addItem(name, price, date)
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.ITEM_ID_ARG) {
                type = NavType.LongType
            })
        ) {
            UpdateItemScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
} 