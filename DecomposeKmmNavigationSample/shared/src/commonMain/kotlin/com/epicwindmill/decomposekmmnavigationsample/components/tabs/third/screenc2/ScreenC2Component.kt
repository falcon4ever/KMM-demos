package com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc2

import com.arkivanov.decompose.ComponentContext

class ScreenC2Component (
    private val componentContext: ComponentContext,
    private val onFinished: (result: Int) -> Unit
) : IScreenC2, ComponentContext by componentContext {

    init {
        backPressedDispatcher.register(::onBackPressed)
    }

    private fun onBackPressed(): Boolean {
        // Return a result to the previous component
        onFinished(1234)

        // Return true to consume the event
        return true
    }
}