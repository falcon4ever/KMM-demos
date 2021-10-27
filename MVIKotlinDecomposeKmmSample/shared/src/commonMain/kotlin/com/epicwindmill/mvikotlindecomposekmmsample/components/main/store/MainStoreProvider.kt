package com.epicwindmill.mvikotlindecomposekmmsample.components.main.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.epicwindmill.mvikotlindecomposekmmsample.api.SwansonQuotes
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.Intent
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


internal class MainStoreProvider(private val storeFactory: StoreFactory,
                                 private val quotesApi: SwansonQuotes
) {

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
    private inner class ExecutorImpl : CoroutineExecutor<Intent, Unit, State, Result, Nothing>() {

        // Action: Called once by the bootstrapper
        override fun executeAction(action: Unit, getState: () -> State) {
            dispatch(Result.QuoteUpdated("Tap refresh for a new quote"))
        }

        // Intent: Called via a user interaction
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.GetRandomQuote -> fetchQuote()
            }
        }

        // Fetch quote via a suspend function and dispatch the result to the reducer.
        private fun fetchQuote() {
            scope.launch {
                val newQuote = withContext(Dispatchers.Default) {
                    quotesApi.getAllQuotes().first()
                }
                dispatch(Result.QuoteUpdated(newQuote))
            }
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
