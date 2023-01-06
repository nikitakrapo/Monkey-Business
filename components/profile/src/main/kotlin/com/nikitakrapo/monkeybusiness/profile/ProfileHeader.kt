package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.UserAvatar
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    emailText: String,
    usernameText: String?,
    profileImageUrl: String?,
) {
    Row(
        modifier = modifier,
    ) {
        UserAvatar(
            modifier = Modifier
                .size(52.dp),
            imageUrl = profileImageUrl,
            onClick = {}
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = usernameText ?: emailText,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun ProfileHeader_Preview() {
    MonkeyTheme {
        Surface {
            ProfileHeader(
                modifier = Modifier.padding(12.dp),
                emailText = "google@yandex.ru",
                usernameText = "nikitakrapo",
                profileImageUrl = null
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun ProfileHeader_Preview_LongEmail() {
    MonkeyTheme {
        Surface {
            ProfileHeader(
                modifier = Modifier.padding(12.dp),
                emailText = "google@yandex.yahoo.com.bebra",
                usernameText = null,
                profileImageUrl = null
            )
        }
    }
}
