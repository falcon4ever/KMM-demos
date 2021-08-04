package com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.essenty.statekeeper.consume
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1.IScreenB1.Model

class ScreenB1Component(
    private val componentContext: ComponentContext,
    private val navigateToB2: () -> Unit
) : IScreenB1, ComponentContext by componentContext {

    private var state: State = stateKeeper.consume(key = "SAVED_STATE") ?: State()

    private val _model = MutableValue(Model(magicNumber = 0))

    override val model: Value<Model> = _model

    init {
        stateKeeper.register("SAVED_STATE") { state }
        _model.reduce {
            it.copy(magicNumber = state.magicNumber)
        }
    }

    override fun navigateToB2Clicked() {
        navigateToB2()
    }

    override fun onResult(value: Int) {
        state = State(magicNumber = value)
        _model.reduce {
            it.copy(magicNumber = state.magicNumber)
        }
    }

    @Parcelize
    private class State(
        val magicNumber: Int = 0
    ) : Parcelable
}