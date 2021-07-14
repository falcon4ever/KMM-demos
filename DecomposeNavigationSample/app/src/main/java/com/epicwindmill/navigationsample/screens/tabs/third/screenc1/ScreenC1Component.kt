package com.epicwindmill.navigationsample.screens.tabs.third.screenc1

import com.arkivanov.decompose.ComponentContext

class ScreenC1Component(
    private val componentContext: ComponentContext,
    private val navigateToC2: () -> Unit
) : IScreenC1, ComponentContext by componentContext {

    override fun navigateToC2Clicked() {
        navigateToC2()
    }
}