package com.nikitakrapo.monkeybusiness.analytics

import com.arkivanov.decompose.ComponentContext

class AnalyticsComponentImpl(
    componentContext: ComponentContext
) : AnalyticsComponent, ComponentContext by componentContext