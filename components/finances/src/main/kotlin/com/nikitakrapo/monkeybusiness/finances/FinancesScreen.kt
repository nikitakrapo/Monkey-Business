package com.nikitakrapo.monkeybusiness.finances

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshDefaults
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsScreen
import com.nikitakrapo.monkeybusiness.finances.accounts.PreviewBankAccountsComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FinancesScreen(
    modifier: Modifier = Modifier,
    component: FinancesComponent,
) {
    val state by component.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = component::refresh,
    )

    Box(modifier = modifier.pullRefresh(pullRefreshState)) {
        BankAccountsScreen(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
            component = component.bankAccountsComponent
        )

        PullRefreshIndicator(
            modifier = Modifier
                .align(Alignment.TopCenter),
            refreshing = state.isRefreshing,
            state = pullRefreshState
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun FinancesScreen_Preview() {
    MonkeyTheme {
        Surface {
            FinancesScreen(component = PreviewFinancesComponent())
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720
)
@Composable
fun FinancesScreen_Preview_Loading() {
    MonkeyTheme {
        Surface {
            FinancesScreen(
                component = PreviewFinancesComponent(
                    isRefreshing = true,
                )
            )
        }
    }
}

fun PreviewFinancesComponent(
    isRefreshing: Boolean = false,
) = object : FinancesComponent {
    override val state: StateFlow<FinancesComponent.State>
        get() = MutableStateFlow(
            FinancesComponent.State(
                isRefreshing = isRefreshing
            ),
        )

    override val bankAccountsComponent: BankAccountsComponent =
        PreviewBankAccountsComponent()

    override fun refresh() {}
}
