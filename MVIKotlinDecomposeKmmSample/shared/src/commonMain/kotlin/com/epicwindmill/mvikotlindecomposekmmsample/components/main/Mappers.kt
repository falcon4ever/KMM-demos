package com.epicwindmill.mvikotlindecomposekmmsample.components.main

import com.epicwindmill.mvikotlindecomposekmmsample.components.main.store.MainStore.State
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.IMain.Model

internal val stateToModel: (State) -> Model = {
        Model(quote = it.quote)
    }