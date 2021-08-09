package com.epicwindmill.decomposekmmnavigationsample.components.tabs.first.screena2

import com.arkivanov.decompose.ComponentContext

class ScreenA2Component (
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit
) : IScreenA2, ComponentContext by componentContext {

    init {
        backPressedDispatcher.register(::onBackPressed)
    }

    override fun onCloseClicked() {
        onFinished()
    }

    private fun onBackPressed(): Boolean {
        onFinished()

        // Return true to consume the event
        return true
    }
}