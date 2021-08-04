package com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc1

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc1.IScreenC1.Model

class ScreenC1Component(
    private val componentContext: ComponentContext,
    private val navigateToC2: () -> Unit
) : IScreenC1, ComponentContext by componentContext {

    private val _model = MutableValue(Model(magicNumber = 0))

    override val model: Value<Model> = _model

    override fun navigateToC2Clicked() {
        navigateToC2()
    }

    override fun onResult(value: Int) {
        _model.reduce { it.copy(magicNumber = value) }
    }
}