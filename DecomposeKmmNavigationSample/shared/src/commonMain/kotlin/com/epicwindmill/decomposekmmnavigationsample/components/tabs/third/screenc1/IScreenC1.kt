package com.epicwindmill.decomposekmmnavigationsample.components.tabs.third.screenc1

import com.arkivanov.decompose.value.Value

interface IScreenC1 {
    fun navigateToC2Clicked()
    fun onResult(value: Int)

    val model: Value<Model>

    data class Model(
        val magicNumber: Int = 0
    )
}