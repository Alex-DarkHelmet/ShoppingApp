package com.alex_bystrov.shoppingapp.data

import com.alex_bystrov.shoppingapp.data.db.ShopItemModel
import kotlinx.coroutines.flow.Flow

interface ShopItemRepository {

    fun getAllItems(): Flow<List<ShopItemModel>>

    fun getShopItem(id: Long): Flow<ShopItemModel?>

    suspend fun updateShopItem(shopItem: ShopItemModel)

    suspend fun deleteShopItem(shopItem: ShopItemModel)

    suspend fun deleteAllShopItems(shopItems: List<ShopItemModel>)

    suspend fun addShopItem(shopItem: ShopItemModel)
}