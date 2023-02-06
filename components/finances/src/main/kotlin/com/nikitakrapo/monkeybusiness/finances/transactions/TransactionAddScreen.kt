package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ButtonDefaults.IconSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitch
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitchItem
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.R
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddComponent.TransactionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAddScreen(
    modifier: Modifier = Modifier,
    component: TransactionAddComponent,
) {
    val state by component.state.collectAsState()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                onClick = focusManager::clearFocus,
                indication = null
            )
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.transaction_adding),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            navigationIcon = {
                IconButton(onClick = component::onBackClicked) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.cd_navigate_back),
                    )
                }
            },
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(TextFieldDefaults.MinHeight)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                enabled = !state.isLoading,
                value = state.nameText,
                onValueChange = component::onNameTextChanged,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                label = { Text(stringResource(R.string.transaction_name_label)) },
                isError = state.nameError != null,
                supportingText = state.nameError?.let {
                    { Text(it) }
                },
                singleLine = true,
                shape = CircleShape
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        SegmentedSwitch(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            enabled = !state.isLoading,
            items = TransactionType.values().map { transactionType ->
                val text = when (transactionType) {
                    TransactionType.Debit -> stringResource(R.string.switch_item_debit)
                    TransactionType.Credit -> stringResource(R.string.switch_item_credit)
                }
                val isSelected = state.selectedTransactionType == transactionType
                SegmentedSwitchItem(
                    label = {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.labelLarge,
                        )
                    },
                    isSelected = isSelected,
                    onSelect = { component.onTransactionTypeSelected(transactionType) },
                )
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        MoneyAmountPicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            amountText = state.amountText,
            onAmountTextChanged = component::onAmountTextChanged,
            selectedCurrency = state.selectedCurrency,
            onCurrencySelected = component::onCurrencySelected,
            enabled = !state.isLoading,
            error = state.amountError,
        )
        Spacer(modifier = Modifier.weight(1f))
        AnimatedVisibility(
            visible = state.error != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            val scrollState = rememberScrollState()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState),
                text = state.error.orEmpty(),
                color = MaterialTheme.colorScheme.error
            )
        }
        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = !state.isLoading,
            onClick = component::onAddClicked
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(IconSize),
                    strokeWidth = 2.dp,
                )
            } else {
                Icon(imageVector = Icons.Default.Done, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.add_transaction_done))
            }
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 500
)
@Composable
fun TransactionAddScreen_Preview() {
    MonkeyTheme {
        Surface {
            TransactionAddScreen(
                component = PreviewTransactionAddComponent()
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 500
)
@Composable
fun TransactionAddScreen_Preview_Errors() {
    MonkeyTheme {
        Surface {
            TransactionAddScreen(
                component = PreviewTransactionAddComponent(
                    hasErrors = true
                )
            )
        }
    }
}

fun PreviewTransactionAddComponent(
    hasErrors: Boolean = false,
) = object : TransactionAddComponent {
    override val state: StateFlow<TransactionAddComponent.State>
        get() = MutableStateFlow(
            TransactionAddComponent.State(
                nameText = "nameText",
                nameError = if (hasErrors) "Sample nameError" else null,
                selectedTransactionType = TransactionType.Default,
                amountText = "moneyAmountText",
                amountError = if (hasErrors) "Sample amountError" else null,
                selectedCurrency = Currency.Default,
                isLoading = false,
                error = if (hasErrors) "Sample error" else null,
            )
        )

    override fun onNameTextChanged(text: String) {}
    override fun onTransactionTypeSelected(type: TransactionType) {}
    override fun onAmountTextChanged(text: String) {}
    override fun onCurrencySelected(currency: Currency) {}
    override fun onBackClicked() {}
    override fun onAddClicked() {}
}