package com.alex_bystrov.shoppingapp.screens.details

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.alex_bystrov.shoppingapp.screens.details.model.ShopItemEvent
import com.alex_bystrov.shoppingapp.screens.details.views.ShopItemDialogView

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel,
    shopItemId: Long? = null
) {
    val viewState = viewModel.shopItemState.collectAsState()

    ShopItemDialogView(
        navController = navController,
        viewState = viewState.value,
        onNameChange = { viewModel.obtainEvent(event = ShopItemEvent.OnNameChanged(name = it)) },
        onSumChange = { viewModel.obtainEvent(event = ShopItemEvent.OnSumChanged(sum = it)) },
        closeDialogAction = { viewModel.obtainEvent(event = ShopItemEvent.CloseDialog) },
        onSaveClicked = {
            viewModel.obtainEvent(
                event = ShopItemEvent.OnSaveClicked(
                    shopItem = viewState.value,
                    id = shopItemId
                )
            )
        },
        isCreated = shopItemId != null,
        onDeleteClicked = {
            viewModel.obtainEvent(
                event = ShopItemEvent.OnDeleteClicked(
                    viewState.value,
                    id = shopItemId
                )
            )
        }
    )


    SideEffect {
        Log.e("TEST", "Side effect call $viewState")
    }

    LaunchedEffect(key1 = shopItemId, block = {
        viewModel.obtainEvent(event = ShopItemEvent.OpenDialog(shopItemId))
    })
}
