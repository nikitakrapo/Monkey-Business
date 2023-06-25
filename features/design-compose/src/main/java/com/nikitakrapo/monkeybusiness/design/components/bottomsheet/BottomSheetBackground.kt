package com.nikitakrapo.monkeybusiness.design.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun BottomSheetBackground(
    modifier: Modifier = Modifier,
    alpha: Float,
    onClick: () -> Unit,
) {
    val bgInteractionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.scrim.copy(alpha = alpha * 0.3f))
            .clickable(
                interactionSource = bgInteractionSource,
                indication = null,
                onClick = onClick,
            ),
    )
}
