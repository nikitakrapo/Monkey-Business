package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyPicker(
    modifier: Modifier = Modifier,
    currenciesList: List<Currency>,
    selectedCurrency: Currency,
    onCurrencySelected: (Currency) -> Unit,
    enabled: Boolean = true,
    isError: Boolean,
) {
    var expandedState by remember { mutableStateOf(false) }
    val expanded = expandedState && enabled

    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expandedState = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .width(100.dp),
            readOnly = true,
            enabled = enabled,
            value = selectedCurrency.code,
            onValueChange = { },
            label = { Text(stringResource(R.string.currency_label)) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation)
                )
            },
            shape = MaterialTheme.shapes.medium.copy(
                bottomStart = ZeroCornerSize,
                topStart = ZeroCornerSize
            ),
            isError = isError,
            supportingText = if (isError) {{}} else null, // needed for MoneyAmountPicker height sync
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expandedState = false
            }
        ) {
            currenciesList.forEach { currency ->
                DropdownMenuItem(
                    text = { Text(currency.code) },
                    onClick = {
                        onCurrencySelected(currency)
                        expandedState = false
                    }
                )
            }
        }
    }
}