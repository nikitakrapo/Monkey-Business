package com.nikitakrapo.monkeybusiness.finances.spendings

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.nikitakrapo.monkeybusiness.finance.models.Spending

fun LazyListScope.SpendingsList(
    spendings: List<Spending>,
    onSpendingClick: (Spending) -> Unit,
) {
    items(spendings) { spending ->
        SpendingCard(
            spending = spending,
            onClick = onSpendingClick,
        )
    }
}
