package com.alex_bystrov.shoppingapp.screens.shopping_main.model

import com.alex_bystrov.shoppingapp.screens.shopping_main.views.ShopItemCardModel

sealed class ShopViewState {
    object NoItems : ShopViewState()
    data class Display(
        val shopItems: List<ShopItemCardModel> = emptyList(),
    ) : ShopViewState()
}