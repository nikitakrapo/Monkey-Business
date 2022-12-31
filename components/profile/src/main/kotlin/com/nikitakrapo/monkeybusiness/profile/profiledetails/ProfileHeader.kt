package com.nikitakrapo.monkeybusiness.profile.profiledetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.profile.R

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    emailText: String,
    usernameText: String?,
    profileImageUrl: String?,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (username, profileImage) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(username) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            text = usernameText ?: emailText,
            style = MaterialTheme.typography.headlineMedium
        )

        AsyncImage(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(12.dp)
                .constrainAs(profileImage) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileImageUrl)
                .build(),
            placeholder = rememberVectorPainter(Icons.Default.Person),
            error = rememberVectorPainter(Icons.Default.Person),
            contentDescription = stringResource(R.string.cd_profile_image)
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
                emailText = "google@yandex.ru",
                usernameText = "nikitakrapo",
                profileImageUrl = null)
        }
    }
}
