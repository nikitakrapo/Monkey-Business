package com.nikitakrapo.mvi

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.nikitakrapo.mvi.feature.Feature

interface FeatureInstance<Intent : Any, State : Any, Event : Any> : InstanceKeeper.Instance {
    val feature: Feature<Intent, State, Event>
}
