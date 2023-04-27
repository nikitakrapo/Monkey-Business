package com.nikitakrapo.monkeybusiness.finances.accounts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.coroutines.mapState
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finance.models.BankCard
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.R
import com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel.BankAccountsScreenViewState
import com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel.toViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BankAccountsScreen(
    modifier: Modifier = Modifier,
    component: BankAccountsComponent,
) {
    val scope = rememberCoroutineScope()
    val state by component.state
        .mapState(scope, BankAccountsComponent.State::toViewState)
        .collectAsState()

    when (val state = state) {
        is BankAccountsScreenViewState.ShowingAccounts -> LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            items(state.accountList.size) { index ->
                val accountViewState = state.accountList[index]
                BankAccountCard(
                    modifier = Modifier.fillMaxWidth(),
                    state = accountViewState,
                    onClick = { component.onAccountClicked(index) }
                )
                Spacer(modifier = Modifier.size(12.dp))
            }
            item {
                FilledTonalButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    onClick = component::onOpenProductClicked,
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.open_new_card_or_product),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        BankAccountsScreenViewState.Loading -> Text("Here should be accounts shimmer..")
    }

}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun BankAccountsScreen_Preview() {
    MonkeyTheme {
        Surface {
            BankAccountsScreen(
                component = PreviewBankAccountsComponent()
            )
        }
    }
}

fun PreviewBankAccountsComponent() = object : BankAccountsComponent {
    override val state: StateFlow<BankAccountsComponent.State> =
        MutableStateFlow(
            BankAccountsComponent.State(
                accountList = listOf(
                    bankAccount(
                        name = "Rubles (salary)",
                        balance = 25692152,
                        currency = Currency.RUB,
                        cards = listOf(
                            bankCard("4567"),
                            bankCard("1242"),
                            bankCard("4276"),
                        ),
                    ),
                    bankAccount(
                        name = "From selling anecdotes",
                        balance = 10234,
                        currency = Currency.USD,
                        cards = listOf(
                            bankCard("9042"),
                        ),
                    ),

                ),
                isLoading = false,
            )
        )

    override fun onAccountClicked(index: Int) {}
    override fun onOpenProductClicked() {}
}

private fun bankAccount(
    name: String,
    balance: Long,
    currency: Currency,
    cards: List<BankCard>,
) = BankAccount(
    iban = "iban",
    name = name,
    balance = balance,
    currency = currency,
    cards = cards,
)

private fun bankCard(
    pan: String,
) = BankCard(
    pan = pan,
)
