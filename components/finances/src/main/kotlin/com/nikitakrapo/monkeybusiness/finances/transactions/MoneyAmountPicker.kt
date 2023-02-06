package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyAmountPicker(
    modifier: Modifier = Modifier,
    amountText: String,
    onAmountTextChanged: (String) -> Unit,
    selectedCurrency: Currency,
    onCurrencySelected: (Currency) -> Unit,
    enabled: Boolean,
    error: String?,
) {
    Row(
        modifier = modifier,
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            enabled = enabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            value = amountText,
            onValueChange = onAmountTextChanged,
            shape = MaterialTheme.shapes.medium.copy(
                bottomEnd = ZeroCornerSize,
                topEnd = ZeroCornerSize
            ),
            trailingIcon = {
                Text(
                    text = selectedCurrency.symbol,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                        .copy(alpha = if (enabled) 1f else 0.4f)
                )
            },
            singleLine = true,
            label = { Text(stringResource(R.string.amount_label)) },
            isError = error != null,
            supportingText = error?.let {
                { Text(it) }
            },
        )
        CurrencyPicker(
            currenciesList = Currency.values().toList(),
            selectedCurrency = selectedCurrency,
            onCurrencySelected = onCurrencySelected,
            enabled = enabled,
            isError = error != null,
        )
    }
}