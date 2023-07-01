package com.nikitakrapo.monkeybusiness.finances.accountdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.resources.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(R.string.cd_navigate_back)
                    )
                }
            }
        )
        Text(
            text = "Bank account",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "12.300,50 $",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun AccountDetailsScreen_Preview() {
    MonkeyTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            AccountDetailsScreen(
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}