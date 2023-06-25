package com.nikitakrapo.monkeybusiness.finances.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun SmallBankCardShimmer(
    modifier: Modifier = Modifier,
    shimmer: Shimmer,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .size(
                width = 45.dp,
                height = 30.dp
            )
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .shimmer(shimmer)
    )
}

@Preview
@Composable
fun SmallBankCardShimmer_Shimmer() {
    MonkeyTheme {
        Surface {
            val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
            SmallBankCardShimmer(shimmer = shimmer)
        }
    }
}
