package com.epicwindmill.mvikotlindecomposekmmsample.components.main

import com.arkivanov.decompose.value.Value

interface IMain {

    val models: Value<Model>

    fun fetchRandomQuote()

    data class Model(
        val quote: String
    )
}