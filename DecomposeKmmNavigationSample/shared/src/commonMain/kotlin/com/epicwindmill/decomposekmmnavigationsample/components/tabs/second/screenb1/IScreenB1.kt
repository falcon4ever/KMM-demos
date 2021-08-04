package com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1

import com.arkivanov.decompose.value.Value

interface IScreenB1 {
    fun navigateToB2Clicked()
    fun onResult(value: Int)

    val model: Value<Model>

    data class Model(
        val magicNumber: Int = 0
    )
}