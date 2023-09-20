package com.alex_bystrov.shoppingapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        baseDarkPalette
    } else baseDarkPalette


    val typography = ShoppingTypography(
        itemText = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    )

    val shapes = ShoppingShape(
        padding = 10.dp
    )


    CompositionLocalProvider(
        LocalShoppingColors provides colors,
        LocalShoppingTypography provides typography,
        LocalShoppingShape provides shapes,
        content = content
    )
}
