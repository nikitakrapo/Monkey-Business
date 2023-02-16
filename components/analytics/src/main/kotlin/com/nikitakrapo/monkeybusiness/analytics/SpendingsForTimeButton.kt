package com.nikitakrapo.monkeybusiness.analytics

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun SpendingsForTimeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Row {
            Text(
                text = "Spendings for ",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp
            )
            Text(
                text = "february",
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun SpendingsForTimeButton_Preview() {
    MonkeyTheme {
        Surface {
            SpendingsForTimeButton(onClick = {})
        }
    }
}