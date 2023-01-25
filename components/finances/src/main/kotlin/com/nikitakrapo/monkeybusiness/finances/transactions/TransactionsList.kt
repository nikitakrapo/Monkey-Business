package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.nikitakrapo.monkeybusiness.finance.models.Transaction

fun LazyListScope.TransactionsList(
    transactions: List<Transaction>,
    onTransactionClick: (Transaction) -> Unit,
) {
    items(transactions) { transaction ->
        TransactionCard(
            transaction = transaction,
            onClick = onTransactionClick,
        )
    }
}
