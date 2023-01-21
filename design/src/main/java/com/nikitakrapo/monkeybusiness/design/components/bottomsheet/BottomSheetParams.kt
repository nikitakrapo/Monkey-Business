package com.nikitakrapo.monkeybusiness.design.components.bottomsheet

import androidx.compose.ui.unit.Dp

data class BottomSheetParams(
    val type: BottomSheetType,
    val offsetAnchors: Map<Dp, Int>,
    val initialState: Int,
)

fun BottomSheetParams.getFirstAnchorHeight(): Dp {
    val maxHeight = offsetAnchors.keys.max()
    return offsetAnchors.keys.minus(maxHeight).max()
}
