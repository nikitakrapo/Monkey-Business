package com.nikitakrapo.monkeybusiness.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.UserAvatar
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    displayName: String,
    profileImageUrl: String?,
    onEditClick: () -> Unit,
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
            text = displayName,
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onEditClick) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.cd_edit_account)
            )
        }
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
                displayName = "nikitakrapo",
                profileImageUrl = null,
                onEditClick = {}
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 720,
)
@Composable
fun ProfileHeader_Preview_LongUsername() {
    MonkeyTheme {
        Surface {
            ProfileHeader(
                modifier = Modifier.padding(12.dp),
                displayName = "longusername16lt",
                profileImageUrl = null,
                onEditClick = {}
            )
        }
    }
}
