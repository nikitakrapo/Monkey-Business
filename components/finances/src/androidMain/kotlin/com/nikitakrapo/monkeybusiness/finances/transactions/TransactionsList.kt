package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.nikitakrapo.monkeybusiness.finance.models.Transaction

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.TransactionsList(
    transactions: List<Transaction>,
    onTransactionClick: (Transaction) -> Unit,
) {
    items(
        items = transactions,
        key = { it.id }
    ) { transaction ->
        TransactionCard(
            modifier = Modifier.animateItemPlacement(),
            transaction = transaction,
            onClick = onTransactionClick,
        )
    }
}

fun LazyListScope.TransactionsListShimmer() {
    items(8) {
        TransactionCardShimmer()
    }
}
