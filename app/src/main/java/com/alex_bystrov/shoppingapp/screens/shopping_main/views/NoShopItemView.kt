package com.alex_bystrov.shoppingapp.screens.shopping_main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alex_bystrov.shoppingapp.R
import com.alex_bystrov.shoppingapp.navigation.Screen
import com.alex_bystrov.shoppingapp.ui.theme.ShoppingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoShopItemView(
    navController: NavController? = null,
) {
    Scaffold(
        contentColor = ShoppingTheme.colors.primaryText,
        containerColor = ShoppingTheme.colors.surfaceColor,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = ShoppingTheme.colors.primaryBackground
                ),
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        color = ShoppingTheme.colors.secondaryText,
                        style = ShoppingTheme.typography.itemText,
                        text = stringResource(id = R.string.title_top), fontSize = 25.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = ShoppingTheme.colors.surfaceColor
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        fontSize = 20.sp,
                        text = stringResource(id = R.string.no_items_text),
                        color = ShoppingTheme.colors.secondaryText,
                        textAlign = TextAlign.Center
                    )
                    Spacer(
                        modifier = Modifier
                            .padding(10.dp)
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { navController?.navigate(Screen.AddShopItem.route) },
                        colors = ButtonDefaults.buttonColors(ShoppingTheme.colors.actionButton)
                    ) {
                        Text(
                            fontSize = 20.sp,
                            color = ShoppingTheme.colors.primaryText,
                            text = stringResource(id = R.string.add_btn)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun NoShopItemViewPreview() {
    NoShopItemView()
}