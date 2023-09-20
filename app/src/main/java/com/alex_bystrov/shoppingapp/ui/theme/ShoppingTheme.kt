package com.alex_bystrov.shoppingapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class ShoppingColors(
    val primaryText: Color,
    val secondaryText: Color,
    val surfaceColor: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val actionButton: Color,
    val deleteButtonText: Color,
    val deleteButtonBackground: Color,
    val saveBackgroundColor: Color,
)

data class ShoppingTypography(
    val itemText: TextStyle,
)

data class ShoppingShape(
    val padding: Dp,
)

object ShoppingTheme {
    val colors: ShoppingColors
        @Composable
        get() = LocalShoppingColors.current

    val typography: ShoppingTypography
        @Composable
        get() = LocalShoppingTypography.current

    val shape: ShoppingShape
        @Composable
        get() = LocalShoppingShape.current
}

val LocalShoppingColors = staticCompositionLocalOf<ShoppingColors> {
    error("No provided colors")
}

val LocalShoppingTypography = staticCompositionLocalOf<ShoppingTypography> {
    error("No provided typography")
}

val LocalShoppingShape = staticCompositionLocalOf<ShoppingShape> {
    error("No provided shape")
}