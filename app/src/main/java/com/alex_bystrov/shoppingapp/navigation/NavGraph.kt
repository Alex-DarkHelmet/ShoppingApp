package com.alex_bystrov.shoppingapp.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alex_bystrov.shoppingapp.screens.ShopMainScreen
import com.alex_bystrov.shoppingapp.screens.details.DetailsScreen
import com.alex_bystrov.shoppingapp.screens.details.DetailsViewModel
import com.alex_bystrov.shoppingapp.screens.shopping_main.ShopViewModel

@SuppressLint("FlowOperatorInvokedInComposition", "StateFlowValueCalledInComposition")
@Composable
fun NavGraph(
    viewModel: ShopViewModel,
    detailsViewModel: DetailsViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.EnterScreen.route) {
        composable(Screen.EnterScreen.route) {
            ShopMainScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(Screen.AddShopItem.route) {
            DetailsScreen(
                navController = navController,
                viewModel = detailsViewModel,
            )
        }
        composable(
            route = Screen.EditShopItem.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
                type = NavType.LongType
            })
        ) {
            Log.d("get_id", it.arguments?.getLong(DETAIL_ARGUMENT_KEY).toString())

            DetailsScreen(
                navController = navController,
                viewModel = detailsViewModel,
                shopItemId = it.arguments?.getLong("id") ?: throw RuntimeException("Wrong shopItemId")
            )
        }
    }
}