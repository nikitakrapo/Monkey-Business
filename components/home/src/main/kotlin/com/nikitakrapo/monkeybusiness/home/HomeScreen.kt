package com.nikitakrapo.monkeybusiness.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.SearchBarButton
import com.nikitakrapo.monkeybusiness.design.components.UserAvatar
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.home.balance.BalanceCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBarButton(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = component::onSearchBarClicked
            )
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            UserAvatar(
                modifier = Modifier.fillMaxHeight(),
                onClick = component::onAvatarClicked
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        BalanceCard(
            balance = state.balance,
            onTopupClicked = component::onTopupClicked,
            onWithdrawClicked = component::onWithdrawClicked
        )
    }
}

@Preview
@Composable
fun HomeScreen_Preview() {
    MonkeyTheme {
        Surface {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                component = PreviewHomeComponent()
            )
        }
    }
}

// TODO: figure out what to do with preview components
fun PreviewHomeComponent(
    moneyAmount: MoneyAmount = MoneyAmount(amount = 201923, currency = Currency.GBP)
) = object : HomeComponent {
    override val state: StateFlow<HomeComponent.State> =
        MutableStateFlow(HomeComponent.State(moneyAmount))

    override fun onSearchBarClicked() {}
    override fun onAvatarClicked() {}
    override fun onTopupClicked() {}
    override fun onWithdrawClicked() {}
}
