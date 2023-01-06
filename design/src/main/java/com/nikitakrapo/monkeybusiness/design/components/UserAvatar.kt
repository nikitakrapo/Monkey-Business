package com.nikitakrapo.monkeybusiness.design.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.background
        ),
        shape = CircleShape,
        border = BorderStroke(1.dp, colorScheme.onSurface)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center),
                imageVector = Icons.Default.Person,
                tint = colorScheme.onSurface,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun UserAvatar_Preview() {
    MonkeyTheme {
        Surface {
            UserAvatar(
                modifier = Modifier.size(92.dp),
                imageUrl = null,
                onClick = {}
            )
        }
    }
}
