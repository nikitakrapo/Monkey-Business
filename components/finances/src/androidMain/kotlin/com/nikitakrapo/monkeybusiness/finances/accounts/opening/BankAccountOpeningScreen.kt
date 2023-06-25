package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.CurrencyNameProvider.getFullName
import com.nikitakrapo.monkeybusiness.finances.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BankAccountOpeningScreen(
    modifier: Modifier = Modifier,
    component: BankAccountOpeningComponent,
) {

    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = component::onBackClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.cd_navigate_back)
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.select_currency),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .navigationBarsPadding(),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                state.currencyList.forEachIndexed { index, currency ->
                    item {
                        CurrencyItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement()
                                .clickable(
                                    onClick = { component.onCurrencySelected(index) },
                                    enabled = !state.isLoading
                                )
                                .padding(horizontal = 12.dp),
                            code = currency.getFullName(LocalContext.current),
                            isSelected = currency == state.selectedCurrency,
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                state.error?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                }
                androidx.compose.animation.AnimatedVisibility(
                    visible = state.isProceedButtonVisible,
                    enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut(),
                ) {
                    FilledTonalButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 48.dp)
                            .animateContentSize(),
                        onClick = component::onProceedClicked,
                        enabled = !state.isLoading
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.CenterVertically),
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically),
                                text = stringResource(
                                    id = R.string.open_account_in_currency_button,
                                    state.currencyList
                                        .firstOrNull { it == state.selectedCurrency }
                                        ?: ""
                                ),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BankAccountOpeningScreen_Preview() {
    MonkeyTheme {
        Surface {
            BankAccountOpeningScreen(
                component = PreviewBankAccountOpeningComponent()
            )
        }
    }
}

@Preview
@Composable
fun BankAccountOpeningScreen_Preview_Loading() {
    MonkeyTheme {
        Surface {
            BankAccountOpeningScreen(
                component = PreviewBankAccountOpeningComponent(
                    isLoading = true
                )
            )
        }
    }
}

@Preview
@Composable
fun BankAccountOpeningScreen_Preview_Error() {
    MonkeyTheme {
        Surface {
            BankAccountOpeningScreen(
                component = PreviewBankAccountOpeningComponent(
                    error = "Some kind of error happened"
                )
            )
        }
    }
}

fun PreviewBankAccountOpeningComponent(
    isLoading: Boolean = false,
    error: String? = null,
) = object : BankAccountOpeningComponent {
    override val state: StateFlow<BankAccountOpeningComponent.State> =
        MutableStateFlow(
            BankAccountOpeningComponent.State(
                currencyList = listOf(
                    Currency.RUB,
                    Currency.USD,
                    Currency.HUF,
                ),
                selectedCurrency = Currency.USD,
                isLoading = isLoading,
                error = error,
            )
        )

    override fun onCurrencySelected(index: Int) {}
    override fun onBackClicked() {}
    override fun onProceedClicked() {}
}