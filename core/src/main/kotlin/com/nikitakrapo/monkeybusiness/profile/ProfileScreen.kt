package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nikitakrapo.monkeybusiness.design.TopNavigationBar

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {}, //TODO: make proper navigation
) {
    Column(
        modifier = modifier,
    ) {
        TopNavigationBar(
            navigationAction = onBackPressed
        )
    }
}