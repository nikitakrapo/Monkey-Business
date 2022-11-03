package com.nikitakrapo.monkeybusiness.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.SearchBarButton
import com.nikitakrapo.monkeybusiness.design.UserAvatar
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.home.balance.BalanceCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    component: HomeComponent,
) {
    val state = component.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SearchBarButton(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight(),
                onClick = component::onSearchBarClicked,
            )
            Spacer(
                modifier = Modifier.width(16.dp),
            )
            UserAvatar(
                modifier = Modifier.fillMaxHeight(),
                onClick = component::onAvatarClicked
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        BalanceCard(
            balance = state.value.balance,
            onTopupClicked = component::onTopupClicked,
            onWithdrawClicked = component::onWithdrawClicked,
        )
    }
}

@Preview
@Composable
fun HomeScreen_Preview() {
    MonkeyTheme {
        Surface {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                component = HomeComponentImpl({},{})
            )
        }
    }
}