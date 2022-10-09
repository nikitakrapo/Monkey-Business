package com.nikitakrapo.monkeybusiness

import kotlinx.coroutines.flow.StateFlow
import ru.yandex.lavka.mvi.feature.FeatureFactory
import ru.yandex.lavka.mvi.feature.createReducerFeature

class CoreComponent(
    initialChild: Core.Child = Core.Child.Home,
    featureFactory: FeatureFactory = FeatureFactory(),
) : Core {

    private val coreFeature = featureFactory.createReducerFeature<Intent, Core.State>(
        name = "Core",
        initialState = Core.State(child = initialChild),
        reducer = {
            when (it) {
                Intent.NavigateHome -> copy(child = Core.Child.Home)
                Intent.NavigateProfile -> copy(child = Core.Child.Profile)
            }
        },
    )

    override val state: StateFlow<Core.State> = coreFeature.state

    override fun onHomeClicked() {
        coreFeature.accept(Intent.NavigateHome)
    }

    override fun onProfileClicked() {
        coreFeature.accept(Intent.NavigateProfile)
    }

    sealed class Intent {
        object NavigateHome : Intent()
        object NavigateProfile : Intent()
    }
}