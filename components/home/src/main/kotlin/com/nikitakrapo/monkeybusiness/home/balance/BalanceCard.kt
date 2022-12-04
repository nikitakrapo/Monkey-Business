package com.nikitakrapo.monkeybusiness.home.balance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.home.R

@Composable
fun BalanceCard(
    modifier: Modifier = Modifier,
    balance: MoneyAmount,
    onTopupClicked: () -> Unit,
    onWithdrawClicked: () -> Unit
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    modifier = Modifier.alignByBaseline(),
                    text = balance.amount.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.alignByBaseline(),
                    text = balance.currency.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FilledTonalButton(onClick = onTopupClicked) {
                    Text(
                        text = stringResource(R.string.topup),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                FilledTonalButton(onClick = onWithdrawClicked) {
                    Text(
                        text = stringResource(R.string.withdraw),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
