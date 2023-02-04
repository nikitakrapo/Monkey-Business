package com.nikitakrapo.mvi

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.nikitakrapo.mvi.feature.Feature
import kotlin.reflect.KProperty

inline operator fun <Intent : Any, State : Any, Event : Any> FeatureInstance<Intent, State, Event>.getValue(
    thisObj: Any?,
    property: KProperty<*>,
) = feature

fun <Intent : Any, State : Any, Event : Any> ComponentContext.createFeature(
    factory: () -> Feature<Intent, State, Event>,
): FeatureInstance<Intent, State, Event> {
    return instanceKeeper.getOrCreate {
        object : FeatureInstance<Intent, State, Event> {
            override val feature: Feature<Intent, State, Event> = factory()

            override fun onDestroy() {
                feature.dispose()
            }
        }
    }
}
