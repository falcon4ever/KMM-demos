package com.epicwindmill.mvikotlindecomposekmmsample.components.main

import com.epicwindmill.mvikotlindecomposekmmsample.components.main.IMain.Model
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.State

internal val stateToModel: (State) -> Model = {
        Model(quote = it.quote)
    }