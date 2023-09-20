package com.alex_bystrov.shoppingapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopItemDao {
    @Query("SELECT * FROM shopItems")
    fun getAllShopItems(): Flow<List<ShopItemModel>>

    @Query("SELECT * FROM shopItems WHERE id = :id")
    fun getShopItem(id: Long): Flow<ShopItemModel>

    @Update
    suspend fun updateShopItemValues(shopItem: ShopItemModel)

    @Delete
    suspend fun deleteShopItem(shopItem: ShopItemModel)

    @Delete
    suspend fun deleteAllShopItems(shopItem: List<ShopItemModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItem: ShopItemModel)
}