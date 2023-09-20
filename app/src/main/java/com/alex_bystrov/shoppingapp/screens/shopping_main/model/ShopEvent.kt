package com.alex_bystrov.shoppingapp.screens.shopping_main.model

sealed class ShopEvent {
    object EnterScreen : ShopEvent()
    data class OnShopItemClick(val id: Long, val newValue: Boolean) : ShopEvent()
    data class OnCheckBoxClicked(val id: Long, val newValue: Boolean) : ShopEvent()
}
