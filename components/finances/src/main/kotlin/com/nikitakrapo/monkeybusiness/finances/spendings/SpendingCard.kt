package com.nikitakrapo.monkeybusiness.finances.spendings

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Spending
import com.nikitakrapo.monkeybusiness.finances.MoneyAmountTextProvider.createText
import kotlinx.datetime.Instant

@Composable
fun SpendingCard(
    modifier: Modifier = Modifier,
    spending: Spending,
    onClick: (Spending) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .clickable { onClick(spending) }
            .padding(vertical = 8.dp)
            .padding(end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
        ) {

        }
        Column {
            Text(
                text = spending.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = spending.id,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            val color = remember(spending.moneyAmount.amount) {
                if (spending.moneyAmount.amount > 0) Color(0xFF388E3C) else null
            }
            val moneyText = remember(spending.moneyAmount) {
                spending.moneyAmount.createText().asString()
            }
            Text(
                text = moneyText,
                style = MaterialTheme.typography.titleMedium,
                color = color ?: MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SpendingCard_Preview() {
    MonkeyTheme {
        Surface {
            SpendingCard(
                modifier = Modifier.width(360.dp),
                spending = Spending(
                    id = "1",
                    moneyAmount = MoneyAmount(1001, Currency.RUB),
                    timestamp = Instant.fromEpochSeconds(0),
                    name = "taxi expenses",
                ),
                onClick = {}
            )
        }
    }
}
