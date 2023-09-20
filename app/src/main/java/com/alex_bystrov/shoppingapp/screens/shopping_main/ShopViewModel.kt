package com.alex_bystrov.shoppingapp.screens.shopping_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex_bystrov.shoppingapp.data.ShopItemRepositoryImpl
import com.alex_bystrov.shoppingapp.data.db.mapToShoItemCardModel
import com.alex_bystrov.shoppingapp.screens.shopping_main.model.ShopEvent
import com.alex_bystrov.shoppingapp.screens.shopping_main.model.ShopViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: ShopItemRepositoryImpl
) : ViewModel() {


    private var _shopState: MutableStateFlow<ShopViewState> =
        MutableStateFlow(ShopViewState.Display())
    val shopState: StateFlow<ShopViewState> = _shopState.asStateFlow()

    fun obtainEvent(event: ShopEvent) {
        when (_shopState.value) {
            is ShopViewState.Display -> reduce(event)
            else -> {}
        }
    }

    private fun reduce(event: ShopEvent) {
        when (event) {
            ShopEvent.EnterScreen -> fetchShopItems()
            is ShopEvent.OnShopItemClick -> onShopItemClick(
                shopItemId = event.id,
            )

            is ShopEvent.OnCheckBoxClicked -> onCheckBoxClicked(
                id = event.id,
                newValue = event.newValue
            )
        }
    }

    private fun onShopItemClick(shopItemId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getShopItem(shopItemId)
            }
        }
    }

    private fun onCheckBoxClicked(id: Long, newValue: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateCheckboxShopItem(id = id, newValue = newValue)
            }
        }
    }

    private fun fetchShopItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getAllItems().collect {
                    val items = it.map { shopItem ->
                        shopItem.mapToShoItemCardModel()
                    }

                    if (items.isEmpty()) {
                        _shopState.update {
                            ShopViewState.NoItems
                        }
                    } else {
                        _shopState.update {
                            ShopViewState.Display(
                                shopItems = items
                            )
                        }
                    }
                }
            }
        }
    }
}
