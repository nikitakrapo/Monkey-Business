package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BankAccountOpeningScreen(
    modifier: Modifier = Modifier,
    component: BankAccountOpeningComponent,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val state by component.state.collectAsState()

    LaunchedEffect(state.isSearchOpened) {
        if (state.isSearchOpened) focusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = MutableInteractionSource(),
            ),
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
                if (!state.isSearchOpened) {
                    Text(
                        text = stringResource(R.string.select_currency),
                        style = MaterialTheme.typography.titleMedium
                    )
                } else {
                    TextField(
                        modifier = Modifier
                            .focusRequester(focusRequester),
                        value = state.query,
                        onValueChange = component::onSearchQueryUpdated,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                modifier = Modifier.padding(start = 1.dp),
                                text = stringResource(R.string.search_common)
                            )
                        }
                    )
                }
            },
            actions = {
                if (!state.isSearchOpened) {
                    IconButton(onClick = component::onSearchClicked) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(R.string.select_currency)
                        )
                    }
                }
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
                state.currencyList.forEachIndexed { index, currencyViewState ->
                    item {
                        CurrencyItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement()
                                .clickable { component.onCurrencySelected(index) }
                                .padding(
                                    vertical = 8.dp,
                                    horizontal = 12.dp
                                ),
                            fullName = currencyViewState.fullName,
                            code = currencyViewState.code,
                            isSelected = currencyViewState.isSelected,
                        )
                    }
                }
            }
            androidx.compose.animation.AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 32.dp),
                visible = state.isProceedButtonVisible,
                enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut(),
            ) {
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = component::onProceedClicked,
                    contentPadding = PaddingValues(
                        horizontal = 30.dp,
                        vertical = 12.dp
                    ),
                    elevation = ButtonDefaults.filledTonalButtonElevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 2.dp,
                        focusedElevation = 2.dp,
                        hoveredElevation = 2.dp,
                    )
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.open_account_in_currency_button,
                            state.currencyList.firstOrNull { it.isSelected }
                                ?.fullName
                                ?: ""
                        ),
                        style = MaterialTheme.typography.titleMedium
                    )
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
fun BankAccountOpeningScreen_Preview_SearchOpened() {
    MonkeyTheme {
        Surface {
            BankAccountOpeningScreen(
                component = PreviewBankAccountOpeningComponent(
                    searchOpened = true
                )
            )
        }
    }
}

fun PreviewBankAccountOpeningComponent(
    searchOpened: Boolean = false,
) = object : BankAccountOpeningComponent {
    override val state: StateFlow<BankAccountOpeningComponent.State> =
        MutableStateFlow(
            BankAccountOpeningComponent.State(
                isSearchOpened = searchOpened,
                isProceedButtonVisible = true,
                query = "",
                currencyList = listOf(
                    CurrencyViewState("Russian Ruble", "RUB", false),
                    CurrencyViewState("Dollar USA", "USD", true),
                    CurrencyViewState("Hungarian Forint", "Ft", false),
                ),
            )
        )

    override fun onCurrencySelected(index: Int) {}
    override fun onSearchQueryUpdated(query: String) {}
    override fun onSearchClicked() {}
    override fun onBackClicked() {}
    override fun onProceedClicked() {}
}