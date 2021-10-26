package com.epicwindmill.decomposekmmnavigationsample.components.tabs.second

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1.IScreenB1
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb1.ScreenB1Component
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb2.IScreenB2
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb2.ScreenB2Component

class ScreenBComponent(
    componentContext: ComponentContext
) : IScreenB, ComponentContext by componentContext {

    private val router =
        router<Config, IScreenB.Child>(
            initialConfiguration = Config.ScreenB1,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, IScreenB.Child>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): IScreenB.Child =
        when (config) {
            is Config.ScreenB1 -> IScreenB.Child.ScreenB1(screenB1(componentContext))
            is Config.ScreenB2 -> IScreenB.Child.ScreenB2(screenB2(componentContext))
        }

    private fun screenB1(componentContext: ComponentContext): IScreenB1 =
        ScreenB1Component(componentContext) {
            router.push(Config.ScreenB2)
        }

    private fun screenB2(componentContext: ComponentContext): IScreenB2 =
        ScreenB2Component(componentContext, onFinished = {
            result ->
            router.pop()
            // The new active child should be Screen B1
            (router.state.value.activeChild.instance as IScreenB.Child.ScreenB1).component.onResult(result)
        })

    private sealed class Config : Parcelable {
        @Parcelize
        object ScreenB1 : Config()

        @Parcelize
        object ScreenB2 : Config()
    }
}