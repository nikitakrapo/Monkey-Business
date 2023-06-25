package com.nikitakrapo.monkeybusiness.finances.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
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
fun BankAccountCardShimmer(
    modifier: Modifier = Modifier,
    shimmer: Shimmer,
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
            .shimmer(shimmer)
            .padding(14.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(44.dp),
        )
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Box(
                modifier = Modifier
                    .size(
                        width = 72.dp,
                        height = 14.dp
                    )
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
            )
            Spacer(modifier = Modifier.size(6.dp))
            Box(
                modifier = Modifier
                    .size(
                        width = 108.dp,
                        height = 14.dp
                    )
                    .clip(RoundedCornerShape(2.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
            )
            Spacer(modifier = Modifier.size(10.dp))
            LazyRow {
                items(3) {
                    SmallBankCardShimmer(shimmer = shimmer)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun BankAccountCardShimmer_Preview() {
    MonkeyTheme {
        Surface {
            val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
            BankAccountCardShimmer(shimmer = shimmer)
        }
    }
}
