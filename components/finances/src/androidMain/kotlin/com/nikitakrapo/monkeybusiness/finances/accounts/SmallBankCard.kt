package com.nikitakrapo.monkeybusiness.finances.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel.SmallBankCardViewState

@Composable
fun SmallBankCard(
    modifier: Modifier = Modifier,
    state: SmallBankCardViewState,
    color: Color = MaterialTheme.colorScheme.secondaryContainer,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .size(
                width = 45.dp,
                height = 30.dp
            )
            .background(color)
            .padding(
                horizontal = 4.dp,
                vertical = 2.dp
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.BottomStart),
            text = state.text,
            color = MaterialTheme.colorScheme.contentColorFor(color),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun SmallBankCard_Preview() {
    MonkeyTheme {
        Surface {
            SmallBankCard(
                state = smallBankCardViewState()
            )
        }
    }
}

private fun smallBankCardViewState() = SmallBankCardViewState(
    text = "4492"
)