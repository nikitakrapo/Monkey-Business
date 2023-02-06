package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitch
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitchItem
import com.nikitakrapo.monkeybusiness.finances.R
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddComponent.TransactionType

@Composable
fun CreditDebitSwitch(
    modifier: Modifier = Modifier,
    selectedTransactionType: TransactionType,
    onTransactionTypeSelected: (TransactionType) -> Unit,
    isEnabled: Boolean,
) {
    SegmentedSwitch(
        modifier = modifier,
        enabled = isEnabled,
        items = TransactionType.values().map { transactionType ->
            val text = when (transactionType) {
                TransactionType.Debit -> stringResource(R.string.switch_item_debit)
                TransactionType.Credit -> stringResource(R.string.switch_item_credit)
            }
            val isSelected = selectedTransactionType == transactionType
            SegmentedSwitchItem(
                label = {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                isSelected = isSelected,
                onSelect = { onTransactionTypeSelected(transactionType) },
            )
        }
    )

}