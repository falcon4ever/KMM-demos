package com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb2

import com.arkivanov.decompose.ComponentContext

class ScreenB2Component (
    private val componentContext: ComponentContext,
    private val onFinished: (result: Int) -> Unit
) : IScreenB2, ComponentContext by componentContext {

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