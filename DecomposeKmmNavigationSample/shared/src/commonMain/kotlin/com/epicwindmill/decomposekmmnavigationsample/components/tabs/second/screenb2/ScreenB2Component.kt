package com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb2

import com.arkivanov.decompose.ComponentContext

class ScreenB2Component (
    private val componentContext: ComponentContext,
    private val onFinished: (result: Int) -> Unit
) : IScreenB2, ComponentContext by componentContext {

    init {
        backPressedHandler.register(::onBackPressed)
    }

    // Used by iOS
    override fun onBackClicked() {
        // Return a result to the previous component
        onFinished(1234)
    }

    // Used by Android
    private fun onBackPressed(): Boolean {
        onBackClicked()

        // Return true to consume the event
        return true
    }
}