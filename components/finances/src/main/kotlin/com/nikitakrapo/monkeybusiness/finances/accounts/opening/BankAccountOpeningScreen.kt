package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
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
                if (!state.isSearchOpened) {
                    Text(text = stringResource(R.string.select_currency))
                } else {
                    OutlinedTextField(value = "sad", onValueChange = {})
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

fun PreviewBankAccountOpeningComponent() = object : BankAccountOpeningComponent {
    override val state: StateFlow<BankAccountOpeningComponent.State> =
        MutableStateFlow(
            BankAccountOpeningComponent.State(
                isSearchOpened = false,
                query = "",
                currencyList = emptyList(),
            )
        )

    override fun onCurrencySelected(index: Int) {}
    override fun onSearchQueryUpdated(query: String) {}
    override fun onSearchClicked() {}
    override fun onBackClicked() {}
    override fun onProceedClicked() {}
}