package com.nikitakrapo.monkeybusiness.finances.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel.BankAccountViewState
import com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel.SmallBankCardViewState
import kotlin.random.Random

@Composable
fun BankAccountCard(
    modifier: Modifier = Modifier,
    state: BankAccountViewState,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
            .clickable(onClick = onClick)
            .padding(14.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(44.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = state.currencySign,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(
                text = state.moneyText,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = state.name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.size(10.dp))
            LazyRow {
                items(state.bankCardList) { smallCardState ->
                    SmallBankCard(
                        state = smallCardState,
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
    }
}

@Preview(
    widthDp = 360
)
@Composable
fun BankAccountCard_Preview_CardList() {
    MonkeyTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                BankAccountCard(
                    modifier = Modifier.fillMaxWidth(),
                    state = bankAccountViewState(
                        cardAmount = 5
                    ),
                    onClick = {}
                )
            }
        }
    }
}

@Preview(
    widthDp = 360
)
@Composable
fun BankAccountCard_Preview_EmptyCardList() {
    MonkeyTheme {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                BankAccountCard(
                    modifier = Modifier.fillMaxWidth(),
                    state = bankAccountViewState(
                        cardAmount = 0
                    ),
                    onClick = {}
                )
            }
        }
    }
}

private fun bankAccountViewState(
    name: String = "Bank account",
    amount: String = "120 023,93",
    currency: String = "Ft",
    cardAmount: Int = 4,
) = BankAccountViewState(
    name = name,
    moneyText = "$amount $currency",
    currencySign = currency,
    bankCardList = smallBankCardViewStateList(cardAmount)
)

private fun smallBankCardViewStateList(
    size: Int
) = buildList {
    (0 until size).forEach { _ ->
        val lastDigits = Random.nextInt(1000, 9999).toString()
        add(SmallBankCardViewState(text = lastDigits))
    }
}

