package com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena1

import com.arkivanov.decompose.ComponentContext

class ScreenA1Component(
    private val componentContext: ComponentContext,
    private val navigateToA2: () -> Unit
) : IScreenA1, ComponentContext by componentContext {

    override fun navigateToA2Clicked() {
        navigateToA2()
    }
}