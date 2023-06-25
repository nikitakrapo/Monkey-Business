package com.nikitakrapo.monkeybusiness.finances.accounts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun BankAccountsShimmer(
    modifier: Modifier = Modifier,
) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(2) {
            BankAccountCardShimmer(
                modifier = Modifier
                    .fillMaxWidth(),
                shimmer = shimmer
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}

@Preview
@Composable
fun BankAccountsShimmer_Preview() {
    MonkeyTheme {
        Surface {
            BankAccountsShimmer()
        }
    }
}
