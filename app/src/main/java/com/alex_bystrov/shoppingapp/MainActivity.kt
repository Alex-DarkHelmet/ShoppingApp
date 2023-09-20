package com.alex_bystrov.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex_bystrov.shoppingapp.navigation.NavGraph
import com.alex_bystrov.shoppingapp.screens.details.DetailsViewModel
import com.alex_bystrov.shoppingapp.screens.shopping_main.ShopViewModel
import com.alex_bystrov.shoppingapp.ui.theme.MainTheme
import com.alex_bystrov.shoppingapp.ui.theme.ShoppingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ShopViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ShoppingTheme.colors.surfaceColor
                ) {
                    NavGraph(
                        viewModel = viewModel,
                        detailsViewModel = detailsViewModel
                    )
                }
            }
        }
    }
}
