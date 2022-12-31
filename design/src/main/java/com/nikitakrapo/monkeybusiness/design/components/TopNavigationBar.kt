package com.nikitakrapo.monkeybusiness.design.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nikitakrapo.monkeybusiness.design.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    navigationAction: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            navigationAction?.let { onClickAction ->
                IconButton(onClick = onClickAction) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.cd_navigate_back)
                    )
                }
            }
        }
    )
}
