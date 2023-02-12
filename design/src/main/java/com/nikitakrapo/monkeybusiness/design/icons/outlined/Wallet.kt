package com.nikitakrapo.monkeybusiness.design.icons.outlined

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Icons.Outlined.Wallet: ImageVector
    get() {
        if (_wallet != null) {
            return _wallet!!
        }
        _wallet = materialIcon(name = "Outlined.AccountBalanceWallet") {
            materialPath {
                moveTo(21.0f, 7.28f)
                verticalLineTo(5.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                horizontalLineTo(5.0f)
                curveToRelative(-1.11f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                verticalLineToRelative(14.0f)
                curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(14.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineToRelative(-2.28f)
                curveToRelative(0.59f, -0.35f, 1.0f, -0.98f, 1.0f, -1.72f)
                verticalLineTo(9.0f)
                curveToRelative(0.0f, -0.74f, -0.41f, -1.37f, -1.0f, -1.72f)
                close()
                moveTo(20.0f, 9.0f)
                verticalLineToRelative(6.0f)
                horizontalLineToRelative(-7.0f)
                verticalLineTo(9.0f)
                horizontalLineToRelative(7.0f)
                close()
                moveTo(5.0f, 19.0f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(14.0f)
                verticalLineToRelative(2.0f)
                horizontalLineToRelative(-6.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                verticalLineToRelative(6.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(6.0f)
                verticalLineToRelative(2.0f)
                horizontalLineTo(5.0f)
                close()
            }
            materialPath {
                moveTo(16.0f, 12.0f)
                moveToRelative(-1.5f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f)
            }
        }
        return _wallet!!
    }

private var _wallet: ImageVector? = null
