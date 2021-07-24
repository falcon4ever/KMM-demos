package com.epicwindmill.mvikotlindecomposekmmsample.components.main.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.epicwindmill.mvikotlindecomposekmmsample.api.SwansonQuotesApi
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.Intent
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal class MainStoreProvider(private val storeFactory: StoreFactory) {

    private val api = SwansonQuotesApi()

    fun provide(): MainStore =
        object : MainStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "MainStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
    ) {}

    private sealed class Result {
        data class QuoteUpdated(val newQuote: String) : Result()
    }

    // Logic should take place in the executor
    private inner class ExecutorImpl : SuspendExecutor<Intent, Unit, State, Result, Nothing>() {

        // Action: Called once by the bootstrapper
        override suspend fun executeAction(action: Unit, getState: () -> State) {
            dispatch(Result.QuoteUpdated("Tap refresh for a new quote"))
        }

        // Intent: Called via a user interaction
        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.GetRandomQuote -> fetchQuote()
            }
        }

        // Fetch quote via a suspend function and dispatch the result to the reducer.
        private suspend fun fetchQuote() {
            val newQuote = withContext(Dispatchers.Default) {
                api.getAllQuotes().first()
            }
            dispatch(Result.QuoteUpdated(newQuote))
        }
    }

    // The reducer processes the result and returns the new state
    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.QuoteUpdated -> copy(quote = result.newQuote)
            }
    }
}
