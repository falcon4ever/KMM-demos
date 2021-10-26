package com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena2

import com.arkivanov.decompose.ComponentContext

class ScreenA2Component (
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit
) : IScreenA2, ComponentContext by componentContext {

    init {
        backPressedHandler.register(::onBackPressed)
    }

    // Used by iOS
    override fun onBackClicked() {
        onFinished()
    }

    // Used by Android
    private fun onBackPressed(): Boolean {
        onFinished()

        // Return true to consume the event
        return true
    }
}