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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BankAccountsScreen(
    modifier: Modifier = Modifier,
    component: BankAccountsComponent,
) {
    val state by component.state.collectAsState()

    LazyColumn(
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
                listOf(
                    bankAccountViewState(
                        name = "Rubles (salary)",
                        amount = "1 129 023,02",
                        currency = "₽",
                        cardAmount = 2
                    ),
                    bankAccountViewState(
                        name = "Forints",
                        cardAmount = 4
                    ),
                )
            )
        )

    override fun onAccountClicked(index: Int) {}
    override fun onOpenProductClicked() {}
}
