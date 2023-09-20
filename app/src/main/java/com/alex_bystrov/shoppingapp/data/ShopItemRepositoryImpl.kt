package com.alex_bystrov.shoppingapp.data

import com.alex_bystrov.shoppingapp.data.db.ShopItemDao
import com.alex_bystrov.shoppingapp.data.db.ShopItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class ShopItemRepositoryImpl(private val shopItemDao: ShopItemDao) : ShopItemRepository {

    override fun getAllItems(): Flow<List<ShopItemModel>> =
        shopItemDao.getAllShopItems()


    override fun getShopItem(id: Long): Flow<ShopItemModel?> =
        shopItemDao.getShopItem(id)


    override suspend fun updateShopItem(shopItem: ShopItemModel) =
        shopItemDao.updateShopItemValues(shopItem)


    override suspend fun deleteShopItem(shopItem: ShopItemModel) =
        shopItemDao.deleteShopItem(shopItem)


    override suspend fun deleteAllShopItems(shopItems: List<ShopItemModel>) {
        shopItemDao.deleteAllShopItems(shopItems)
    }

    override suspend fun addShopItem(shopItem: ShopItemModel) =
        shopItemDao.addShopItem(shopItem)


    suspend fun updateCheckboxShopItem(id: Long, newValue: Boolean) {
        val shopItem = shopItemDao.getShopItem(id).firstOrNull()
        if (shopItem != null) {
            val updatedCheckBoxItem = shopItem.copy(
                isFinished = newValue
            )
            shopItemDao.updateShopItemValues(updatedCheckBoxItem)
        }
    }
}
