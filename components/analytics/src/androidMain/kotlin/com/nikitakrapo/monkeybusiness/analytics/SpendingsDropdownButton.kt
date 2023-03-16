package com.nikitakrapo.monkeybusiness.analytics

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun SpendingsDropdownButton(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onClick: () -> Unit,
) {
    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = "192394,32$",
            fontSize = 16.sp
        )
        Icon(
            modifier = Modifier.rotate(rotation),
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun SpendingsDropdownButton_Preview_Collapsed() {
    MonkeyTheme {
        Surface {
            SpendingsDropdownButton(
                expanded = false,
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun SpendingsDropdownButton_Preview_Expanded() {
    MonkeyTheme {
        Surface {
            SpendingsDropdownButton(
                expanded = true,
                onClick = {}
            )
        }
    }
}
