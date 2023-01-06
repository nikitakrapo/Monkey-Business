package com.nikitakrapo.monkeybusiness.finances

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finances.balance.BalanceCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FinancesScreen(
    modifier: Modifier = Modifier,
    component: FinancesComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        BalanceCard(
            balance = state.moneyAmount,
            onDepositClicked = component::onDepositClicked,
            onWithdrawClicked = component::onWithdrawClicked
        )
    }
}

fun PreviewFinancesComponent() = object : FinancesComponent {
    override val state: StateFlow<FinancesComponent.State>
        get() = MutableStateFlow(
            FinancesComponent.State(
                moneyAmount = MoneyAmount(
                    amount = 123456,
                    currency = Currency.GBP
                )
            )
        )

    override fun onDepositClicked() {}
    override fun onWithdrawClicked() {}
}
