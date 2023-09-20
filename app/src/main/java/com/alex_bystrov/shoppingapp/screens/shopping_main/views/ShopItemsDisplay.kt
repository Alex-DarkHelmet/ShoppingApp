package com.alex_bystrov.shoppingapp.screens.shopping_main.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alex_bystrov.shoppingapp.R
import com.alex_bystrov.shoppingapp.navigation.Screen
import com.alex_bystrov.shoppingapp.screens.shopping_main.model.ShopViewState
import com.alex_bystrov.shoppingapp.ui.theme.ShoppingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopItemsDisplay(
    navController: NavController?,
    viewState: ShopViewState.Display,
    onShopItemClick: (ShopItemCardModel) -> Unit,
    onCheckBoxClick: (Long, Boolean) -> Unit
) {
    Scaffold(
        contentColor = ShoppingTheme.colors.primaryText,
        containerColor = ShoppingTheme.colors.surfaceColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = ShoppingTheme.colors.surfaceColor
                ),
                modifier = Modifier.fillMaxWidth().padding(bottom = ShoppingTheme.shape.padding),
                title = {
                    Text(
                        color = ShoppingTheme.colors.secondaryText,
                        style = ShoppingTheme.typography.itemText,
                        text = stringResource(id = R.string.title_top), fontSize = 25.sp
                    )
                })
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(end = 15.dp, bottom = 40.dp),
                containerColor = ShoppingTheme.colors.actionButton,
                contentColor = ShoppingTheme.colors.primaryText,
                shape = ShapeDefaults.Large,
                onClick = {
                    navController?.navigate(Screen.AddShopItem.route)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_new_shopping_item_button)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            item {
                viewState.shopItems.forEach { model ->
                    ShopItemCard(
                        modifier = Modifier,
                        model = model,
                        onCardClicked = { onShopItemClick(model) },
                        onCheckChange = { onCheckBoxClick(model.id, !model.isFinish) }
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ShopItemsDisplayPreview() {
//    ShopItemsDisplay(
//        navController = null,
//        viewState = ShopViewState.Display(
//            shopItems = listOf(
//                ShopItemCardModel(name = "milk", sum = 10),
//                ShopItemCardModel(name = "milk", sum = 10),
//                ShopItemCardModel(name = "milk", sum = 10),
//            )
//        )
//    )
//}

