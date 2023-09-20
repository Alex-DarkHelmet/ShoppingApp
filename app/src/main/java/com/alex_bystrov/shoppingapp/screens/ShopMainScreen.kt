package com.alex_bystrov.shoppingapp.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.alex_bystrov.shoppingapp.navigation.Screen
import com.alex_bystrov.shoppingapp.screens.shopping_main.ShopViewModel
import com.alex_bystrov.shoppingapp.screens.shopping_main.model.ShopEvent
import com.alex_bystrov.shoppingapp.screens.shopping_main.model.ShopViewState
import com.alex_bystrov.shoppingapp.screens.shopping_main.views.NoShopItemView
import com.alex_bystrov.shoppingapp.screens.shopping_main.views.ShopItemsDisplay

@Composable
fun ShopMainScreen(
    navController: NavController,
    viewModel: ShopViewModel,
) {
    val viewState = viewModel.shopState.collectAsState(initial = ShopViewState.Display())

    when (val state = viewState.value) {
        is ShopViewState.Display -> ShopItemsDisplay(
            navController = navController,
            viewState = state,
            onShopItemClick = {
                val shopItem = state.shopItems.find { item ->
                    it.id == item.id
                }
                Log.d("get_key", shopItem.toString())

                navController.navigate(
                    Screen.EditShopItem.passId(
                        shopItem?.id
                            ?: throw RuntimeException("ShopItemId is not found - $shopItem")
                    )
                )
            },
            onCheckBoxClick = { id, newValue ->
                val itemId = state.shopItems.find { item ->
                    item.id == id
                }?.id ?: throw RuntimeException("Invalid id - $id")
                viewModel.obtainEvent(ShopEvent.OnCheckBoxClicked(id = itemId, newValue = newValue))
            }
        )

        ShopViewState.NoItems -> NoShopItemView(navController = navController)
    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainEvent(ShopEvent.EnterScreen)
    })
}
