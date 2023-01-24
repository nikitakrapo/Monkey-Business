package com.nikitakrapo.monkeybusiness.finances

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Spending
import com.nikitakrapo.monkeybusiness.finances.balance.BalanceCard
import com.nikitakrapo.monkeybusiness.finances.spendings.SpendingsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Instant

@Composable
fun FinancesScreen(
    modifier: Modifier = Modifier,
    component: FinancesComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier,
    ) {
        BalanceCard(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            balance = state.moneyAmount,
            onDepositClicked = component::onDepositClicked,
            onWithdrawClicked = component::onWithdrawClicked
        )
        SpendingsList(
            modifier = Modifier,
            spendings = state.spendingsList,
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
                ),
                spendingsList = listOf(
                    Spending(
                        id = "",
                        moneyAmount = MoneyAmount(1000, Currency.RUB),
                        timestamp = Instant.fromEpochSeconds(0),
                        name = "taxi expenses",
                    )
                )
            )
        )

    override fun onDepositClicked() {}
    override fun onWithdrawClicked() {}
}
