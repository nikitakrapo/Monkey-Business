package com.nikitakrapo.monkeybusiness.design

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun SearchBarButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val shape = remember { RoundedCornerShape(50) }
    Row(
        modifier = modifier
            .clip(shape)
            .border(1.dp, colorScheme.outline, shape = shape)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(R.string.search),
            style = typography.bodyLarge,
            color = colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
fun SearchBar_Preview() {
    MonkeyTheme {
        Surface {
            SearchBarButton(
                modifier = Modifier.size(
                    width = 300.dp,
                    height = 48.dp,
                ),
                onClick = {},
            )
        }
    }
}