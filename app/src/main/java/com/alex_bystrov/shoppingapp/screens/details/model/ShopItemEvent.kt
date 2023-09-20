package com.alex_bystrov.shoppingapp.screens.details.model

sealed class ShopItemEvent {
    object CloseDialog: ShopItemEvent()
    data class OpenDialog(val shopItemId: Long? = null): ShopItemEvent()
    data class OnNameChanged(val name: String) : ShopItemEvent()
    data class OnSumChanged(val sum: String) : ShopItemEvent()
    data class OnSaveClicked(val shopItem: DetailsViewState, val id: Long?) : ShopItemEvent()
    data class OnDeleteClicked(val shopItem: DetailsViewState, val id: Long?) : ShopItemEvent()
}