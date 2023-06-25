package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun CurrencyItem(
    modifier: Modifier = Modifier,
    code: String,
    isSelected: Boolean,
) {
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 54.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = code,
            style = MaterialTheme.typography.titleMedium,
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun CurrencyItem_Preview() {
    MonkeyTheme {
        Surface {
            CurrencyItem(
                modifier = Modifier.width(300.dp),
                code = "RUB",
                isSelected = false
            )
        }
    }
}

@Preview
@Composable
fun CurrencyItem_Preview_Selected() {
    MonkeyTheme {
        Surface {
            CurrencyItem(
                modifier = Modifier.width(300.dp),
                code = "RUB",
                isSelected = true
            )
        }
    }
}
