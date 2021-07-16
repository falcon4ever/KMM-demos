package com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1

import com.arkivanov.decompose.ComponentContext

class ScreenB1Component(
    private val componentContext: ComponentContext,
    private val navigateToB2: () -> Unit
) : IScreenB1, ComponentContext by componentContext {

    override fun navigateToB2Clicked() {
        navigateToB2()
    }
}