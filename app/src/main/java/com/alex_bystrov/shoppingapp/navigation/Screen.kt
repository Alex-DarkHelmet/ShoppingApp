package com.alex_bystrov.shoppingapp.navigation


const val DETAIL_ARGUMENT_KEY = "id"

sealed class Screen(val route: String) {
    object EnterScreen: Screen(route = "enter_screen")
    object AddShopItem: Screen(route = "add_shopItem")
    object EditShopItem: Screen(route = "edit_shopItem/{$DETAIL_ARGUMENT_KEY}") {
        fun passId(id: Long): String {
            return "edit_shopItem/$id"
        }
    }
}
