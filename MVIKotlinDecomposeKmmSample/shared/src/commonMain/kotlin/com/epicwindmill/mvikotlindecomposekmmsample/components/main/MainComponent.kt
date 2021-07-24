package com.epicwindmill.mvikotlindecomposekmmsample.components.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStoreProvider
import com.epicwindmill.mvikotlindecomposekmmsample.utils.asValue
import com.epicwindmill.mvikotlindecomposekmmsample.utils.getStore

class MainComponent (
    private val componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : IMain, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
            MainStoreProvider(storeFactory = storeFactory).provide()
        }

    override val models: Value<IMain.Model> = store.asValue().map(stateToModel)

    override fun fetchRandomQuote() {
        store.accept(MainStore.Intent.GetRandomQuote)
    }
}