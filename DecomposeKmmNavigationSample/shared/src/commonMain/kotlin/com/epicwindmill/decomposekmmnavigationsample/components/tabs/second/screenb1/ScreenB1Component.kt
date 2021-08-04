package com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.reduce
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1.IScreenB1.Model

class ScreenB1Component(
    private val componentContext: ComponentContext,
    private val navigateToB2: () -> Unit
) : IScreenB1, ComponentContext by componentContext {

    private val _model = MutableValue(Model(magicNumber = 0))

    override val model: Value<Model> = _model

    override fun navigateToB2Clicked() {
        navigateToB2()
    }

    override fun onResult(value: Int) {
        _model.reduce { it.copy(magicNumber = value) }
    }
}