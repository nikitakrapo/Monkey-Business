package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitch
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitchItem
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAddScreen(
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                onClick = focusManager::clearFocus,
                indication = null
            )
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.transaction_adding),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.cd_navigate_back),
                    )
                }
            },
        )
        var text by remember {
            mutableStateOf("")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .size(TextFieldDefaults.MinHeight)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                value = text,
                onValueChange = { text = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                label = { Text(stringResource(R.string.transaction_name_label)) },
                singleLine = true,
                shape = CircleShape
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        var selected by remember {
            mutableStateOf(0)
        }
        SegmentedSwitch(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            items = listOf(
                SegmentedSwitchItem(
                    label = {
                        Text(
                            text = stringResource(R.string.switch_item_credit),
                            style = MaterialTheme.typography.labelLarge,
                            color = if (selected == 0) {
                                MaterialTheme.colorScheme.onSecondaryContainer
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                        )
                    },
                    isSelected = selected == 0,
                    onSelect = { selected = 0 },
                ),
                SegmentedSwitchItem(
                    label = {
                        Text(
                            text = stringResource(R.string.switch_item_debit),
                            style = MaterialTheme.typography.labelLarge,
                            color = if (selected == 1) {
                                MaterialTheme.colorScheme.onSecondaryContainer
                            } else {
                                MaterialTheme.colorScheme.onSurface
                            }
                        )
                    },
                    isSelected = selected == 1,
                    onSelect = { selected = 1 },
                )
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
        ) {
            var currency by remember { mutableStateOf(Currency.RUB) }
            var amountText by remember { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                value = amountText,
                onValueChange = { amountText = it },
                shape = MaterialTheme.shapes.medium.copy(
                    bottomEnd = ZeroCornerSize,
                    topEnd = ZeroCornerSize
                ),
                trailingIcon = {
                    Text(
                        text = currency.symbol,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                singleLine = true,
                label = { Text(stringResource(R.string.amount_label)) }
            )
            CurrencyPicker(
                currenciesList = Currency.values().toList(),
                selectedCurrency = currency,
                onCurrencySelected = { currency = it },
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.add_transaction_done))
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 360
)
@Composable
fun TransactionAddScreen_Preview() {
    MonkeyTheme {
        Surface {
            TransactionAddScreen()
        }
    }
}