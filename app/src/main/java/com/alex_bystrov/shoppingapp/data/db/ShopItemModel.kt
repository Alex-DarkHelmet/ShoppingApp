package com.alex_bystrov.shoppingapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alex_bystrov.shoppingapp.screens.shopping_main.views.ShopItemCardModel

@Entity(tableName = "shopItems")
data class ShopItemModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sum") val sum: Int,
    @ColumnInfo(name = "finish", defaultValue = "finish") val isFinished: Boolean,
//    @ColumnInfo(name = "category") val category: ShopCategory
)

fun ShopItemModel.mapToShoItemCardModel(): ShopItemCardModel =
    ShopItemCardModel(
        id = id,
        name = name,
        sum = sum.toString(),
        isFinish = isFinished
    )

//data class ShopCategory(
//    val name: String
//)
