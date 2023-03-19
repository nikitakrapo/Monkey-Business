package com.nikitakrapo.monkeybusiness.finances.opening

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.monkeybusiness.design.components.bottomsheet.BottomSheetDefaults
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.finances.R
import com.nikitakrapo.monkeybusiness.finances.products.ProductOpeningComponent

@Composable
fun ProductOpeningScreen(
    modifier: Modifier = Modifier,
    component: ProductOpeningComponent,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = BottomSheetDefaults.dragHandlePadding + 16.dp)
            .padding(horizontal = 16.dp),
    ) {
        val productItemModifier = @Composable { onClick: () -> Unit ->
            Modifier
                .weight(1f)
                .clip(MaterialTheme.shapes.medium)
                .clickable(onClick = onClick)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .padding(
                    horizontal = 16.dp,
                    vertical = 6.dp
                )
        }
        Column(
            modifier = productItemModifier(component::onOpenCardSelected)
                .height(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Open card",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.cards_preview),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = productItemModifier(component::onOpenAccountSelected)
                .height(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Open account",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.currencies_preview),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 360
)
@Composable
fun ProductOpeningScreen_Preview() {
    MonkeyTheme {
        Surface {
            ProductOpeningScreen(
                component = PreviewProductOpeningComponent()
            )
        }
    }
}

fun PreviewProductOpeningComponent() = object : ProductOpeningComponent {
    override fun onOpenCardSelected() {}
    override fun onOpenAccountSelected() {}
}