package com.alex_bystrov.shoppingapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ShopItemModel::class],
    exportSchema = true
)
abstract class ShopDataBase : RoomDatabase() {
    abstract fun shopItemDao(): ShopItemDao
}