package com.alex_bystrov.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.alex_bystrov.shoppingapp.data.ShopItemRepositoryImpl
import com.alex_bystrov.shoppingapp.data.db.ShopDataBase
import com.alex_bystrov.shoppingapp.data.db.ShopItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ShopDataBase {
        return Room.databaseBuilder(
            appContext,
            ShopDataBase::class.java,
            "shop_database"
        ).build()
    }

    @Provides
    fun provideDao(dataBase: ShopDataBase): ShopItemDao {
        return dataBase.shopItemDao()
    }
//
    @Provides
    fun provideRepository(shopItemDao: ShopItemDao): ShopItemRepositoryImpl {
        return ShopItemRepositoryImpl(shopItemDao)
    }
}
