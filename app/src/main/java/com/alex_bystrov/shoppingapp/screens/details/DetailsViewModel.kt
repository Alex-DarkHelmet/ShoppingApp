package com.alex_bystrov.shoppingapp.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex_bystrov.shoppingapp.data.ShopItemRepositoryImpl
import com.alex_bystrov.shoppingapp.screens.details.model.DetailsViewState
import com.alex_bystrov.shoppingapp.screens.details.model.ShopItemEvent
import com.alex_bystrov.shoppingapp.screens.shopping_main.views.ShopItemCardModel
import com.alex_bystrov.shoppingapp.screens.shopping_main.views.mapToShopItemDbModel
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
class DetailsViewModel @Inject constructor(
    private val repository: ShopItemRepositoryImpl
) : ViewModel() {

    private val _shopItemState = MutableStateFlow(DetailsViewState())
    val shopItemState: StateFlow<DetailsViewState> = _shopItemState.asStateFlow()

    fun obtainEvent(event: ShopItemEvent) {
        when (event) {
            is ShopItemEvent.OnNameChanged -> updateName(inputName = event.name)
            is ShopItemEvent.OnSumChanged -> updateSum(inputSum = event.sum)
            is ShopItemEvent.OnSaveClicked -> saveShopItemToDb(
                currentItem = event.shopItem,
                id = event.id
            )

            is ShopItemEvent.OpenDialog -> fetchShopItem(shopItemId = event.shopItemId)
            ShopItemEvent.CloseDialog -> deleteCurrentValueFromUi()
            is ShopItemEvent.OnDeleteClicked -> deleteCurrentItemFromDb(
                currentItem = event.shopItem,
                id = event.id
            )
        }
    }

    private fun updateName(inputName: String) {
        _shopItemState.update {
            it.copy(
                name = inputName
            )
        }
    }

    private fun updateSum(inputSum: String) {
        _shopItemState.update {
            it.copy(
                sum = inputSum,
            )
        }
    }

    private fun fetchShopItem(shopItemId: Long? = null) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (shopItemId != null) {
                    repository.getShopItem(shopItemId).collect { shopItemDb ->


                        val chosenShopItem = ShopItemCardModel(
                            id = shopItemId,
                            name = shopItemDb?.name.toString(),
                            sum = shopItemDb?.sum.toString()
                        )

                        _shopItemState.update {
                            it.copy(
                                name = chosenShopItem.name,
                                sum = chosenShopItem.sum
                            )
                        }
                    }
                } else {
                    deleteCurrentValueFromUi()
                }
            }
        }
    }

    private fun deleteCurrentValueFromUi() {
        _shopItemState.update {
            it.copy(
                name = "",
                sum = ""
            )
        }
    }

    private fun addShopItem(currentItem: DetailsViewState) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.addShopItem(
                    shopItem = ShopItemCardModel(
                        name = currentItem.name,
                        sum = currentItem.sum
                    ).mapToShopItemDbModel()
                )
            }
        }
    }

    private fun updateShopItemValues(currentItem: DetailsViewState, id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (currentItem.name.isNotEmpty() && currentItem.sum.isNotEmpty()) {
                    repository.updateShopItem(
                        shopItem = ShopItemCardModel(
                            id = id,
                            name = currentItem.name,
                            sum = currentItem.sum
                        ).mapToShopItemDbModel()
                    )
                }
            }
        }
    }

    private fun saveShopItemToDb(currentItem: DetailsViewState, id: Long?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (id != null) {
                    updateShopItemValues(currentItem, id)
                } else {
                    addShopItem(currentItem)
                }
            }
        }
    }

    private fun deleteCurrentItemFromDb(currentItem: DetailsViewState, id: Long?) {
        if (id != null) {
            viewModelScope.launch {
                repository.deleteShopItem(
                    shopItem = ShopItemCardModel(
                        id = id,
                        name = currentItem.name,
                        sum = currentItem.sum
                    ).mapToShopItemDbModel()
                )
            }
        }
        deleteCurrentValueFromUi()
    }
}
