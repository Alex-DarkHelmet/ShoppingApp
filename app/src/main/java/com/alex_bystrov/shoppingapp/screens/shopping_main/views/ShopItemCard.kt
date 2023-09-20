package com.alex_bystrov.shoppingapp.screens.shopping_main.views

import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex_bystrov.shoppingapp.data.db.ShopItemModel
import com.alex_bystrov.shoppingapp.ui.theme.ShoppingTheme
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopItemCardModel(
    val id: Long = 0,
    var name: String = "",
    var sum: String = "",
    var isFinish: Boolean = false
) : Parcelable

fun ShopItemCardModel.mapToShopItemDbModel(): ShopItemModel =
    ShopItemModel(
        id = id,
        name = name,
        sum = sum.toInt(),
        isFinished = isFinish
    )


@Composable
fun ShopItemCard(
    modifier: Modifier,
    model: ShopItemCardModel,
    onCardClicked: (() -> Unit)? = null,
    onCheckChange: ((Boolean) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(4.dp)
            .clickable {
                onCardClicked?.invoke()
            },

        elevation = CardDefaults.cardElevation(20.dp),
        colors = if (!model.isFinish) {
            cardColors(ShoppingTheme.colors.primaryBackground)
        } else {
            cardColors(ShoppingTheme.colors.secondaryBackground)
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = if (model.isFinish) {
                    ShoppingTheme.colors.secondaryText
                } else {
                    ShoppingTheme.colors.primaryText
                },
                style = ShoppingTheme.typography.itemText,
                text = model.name,
            )

            Text(
                modifier = Modifier
                    .padding(end = ShoppingTheme.shape.padding),
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                color = if (model.isFinish) {
                    ShoppingTheme.colors.secondaryText
                } else {
                    ShoppingTheme.colors.primaryText
                },
                style = ShoppingTheme.typography.itemText,
                text = model.sum,
            )

            Checkbox(
                modifier = Modifier
                    .padding(end = ShoppingTheme.shape.padding),
                checked = model.isFinish,
                onCheckedChange = onCheckChange,
                colors = CheckboxDefaults.colors(ShoppingTheme.colors.primaryText)
            )
        }
    }
}

@Preview
@Composable
fun ShopItemCardPreview() {
    ShopItemCard(
        modifier = Modifier,
        model = ShopItemCardModel(
            id = 1,
            name = "Milk",
            sum = "10",
            isFinish = false
        )
    )
}
