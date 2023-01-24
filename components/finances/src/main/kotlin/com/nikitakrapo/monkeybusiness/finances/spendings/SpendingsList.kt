package com.nikitakrapo.monkeybusiness.finances.spendings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikitakrapo.monkeybusiness.finance.models.Spending

@Composable
fun SpendingsList(
    modifier: Modifier = Modifier,
    spendings: List<Spending>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(spendings) { spending ->
            SpendingCard(spending = spending)
        }
    }
}
