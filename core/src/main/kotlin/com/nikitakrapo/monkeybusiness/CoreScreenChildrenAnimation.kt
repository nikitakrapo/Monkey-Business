package com.nikitakrapo.monkeybusiness

import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation

@OptIn(ExperimentalDecomposeApi::class)
fun coreScreenChildrenAnimation(): StackAnimation<Any, CoreComponent.Child> =
    stackAnimation { child, otherChild, direction ->
        return@stackAnimation if (
            child.instance is CoreComponent.Child.Authentication
            || otherChild.instance is CoreComponent.Child.Authentication
        ) {
            fade()
        } else {
            slide()
        }
    }