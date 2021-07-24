package com.epicwindmill.mvikotlindecomposekmmsample.components.main.store

import com.arkivanov.mvikotlin.core.store.Store
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.Intent
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.State

internal interface MainStore : Store<Intent, State, Nothing> {

    sealed class Intent {
        object GetRandomQuote : Intent()
    }

    data class State(
        val quote: String = ""
    )
}