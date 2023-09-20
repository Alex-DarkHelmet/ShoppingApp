package com.alex_bystrov.shoppingapp.screens.details.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alex_bystrov.shoppingapp.R
import com.alex_bystrov.shoppingapp.navigation.Screen
import com.alex_bystrov.shoppingapp.screens.details.model.DetailsViewState
import com.alex_bystrov.shoppingapp.ui.theme.ShoppingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopItemDialogView(
    viewState: DetailsViewState,
    navController: NavController?,
    onNameChange: (String) -> Unit,
    onSumChange: (String) -> Unit,
    onSaveClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    closeDialogAction: () -> Unit,
    isCreated: Boolean,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        AlertDialog(
            onDismissRequest = {
                closeDialogAction()
                navController?.popBackStack()
            },
            containerColor = ShoppingTheme.colors.primaryBackground,
            textContentColor = ShoppingTheme.colors.primaryText,
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        style = ShoppingTheme.typography.itemText,
                        text = if (isCreated) {
                            stringResource(id = R.string.edit_title)
                        } else {
                            stringResource(id = R.string.add_title)
                        },
                    )

                    OutlinedTextField(
                        value = viewState.name,
                        onValueChange = {
                            onNameChange(it)
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.name_title),
                                color = ShoppingTheme.colors.primaryText,
                                fontSize = 15.sp,
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = ShoppingTheme.colors.surfaceColor,
                            unfocusedBorderColor = ShoppingTheme.colors.surfaceColor
                        ),
                        textStyle = ShoppingTheme.typography.itemText,
                        maxLines = 2,
                    )

                    OutlinedTextField(
                        value = viewState.sum,
                        onValueChange = {
                            onSumChange(it)
                        },
                        label = {
                            Text(
                                text = stringResource(id = R.string.sum_title),
                                fontSize = 15.sp,
                                color = ShoppingTheme.colors.primaryText
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = ShoppingTheme.colors.surfaceColor,
                            unfocusedBorderColor = ShoppingTheme.colors.surfaceColor
                        ),
                        textStyle = ShoppingTheme.typography.itemText,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        maxLines = 1,
                    )
                }
            },
            confirmButton = {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 5.dp),
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    onClick = {
                        onSaveClicked()
                        closeDialogAction()
                        navController?.navigate(Screen.EnterScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(ShoppingTheme.colors.saveBackgroundColor),
                    enabled = (viewState.name.isNotEmpty() && viewState.sum.isNotEmpty())
                ) {
                    Text(
                        text = stringResource(id = R.string.save_title),
                        style = ShoppingTheme.typography.itemText,
                        color = ShoppingTheme.colors.primaryText
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 5.dp),
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    onClick = {
                        closeDialogAction()
                        onDeleteClicked()
                        navController?.navigate(Screen.EnterScreen.route)
                    },
                    enabled = isCreated,
                    colors = ButtonDefaults.buttonColors(ShoppingTheme.colors.deleteButtonBackground)
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_title),
                        color = ShoppingTheme.colors.deleteButtonText,
                        fontSize = 20.sp
                    )
                }
            }
        )
    }
}

@Composable
@Preview
fun ShopItemDialogViewPreview() {
    ShopItemDialogView(
        viewState = DetailsViewState(
            name = "milk",
            sum = "10"
        ),
        navController = null,
        onNameChange = { },
        onSumChange = { },
        onSaveClicked = {},
        onDeleteClicked = {},
        closeDialogAction = {},
        isCreated = true
    )
}
