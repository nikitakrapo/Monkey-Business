package com.nikitakrapo.monkeybusiness.finances.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitch
import com.nikitakrapo.monkeybusiness.design.components.SegmentedSwitchItem
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAddScreen(
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                onClick = focusManager::clearFocus,
                indication = null
            )
            .padding(horizontal = 12.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            //enabled = !state.isLoading,
            value = "",
            onValueChange = { },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
            label = { Text(stringResource(R.string.transaction_name_label)) },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(8.dp))
        var selected by remember {
            mutableStateOf(0)
        }
        SegmentedSwitch(
            modifier = Modifier
                .fillMaxWidth(),
            items = listOf(
                SegmentedSwitchItem(
                    label = {
                        Text(
                            text = stringResource(R.string.switch_item_expense),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    isSelected = selected == 0,
                    onSelect = { selected = 0 },
                ),
                SegmentedSwitchItem(
                    label = {
                        Text(text = stringResource(R.string.switch_item_income))
                    },
                    isSelected = selected == 1,
                    onSelect = { selected = 1 },
                )
            )
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 360
)
@Composable
fun TransactionAddScreen_Preview() {
    MonkeyTheme {
        Surface {
            TransactionAddScreen()
        }
    }
}