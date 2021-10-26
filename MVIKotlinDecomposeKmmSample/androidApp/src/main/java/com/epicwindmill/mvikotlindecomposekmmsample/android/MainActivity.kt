package com.epicwindmill.mvikotlindecomposekmmsample.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.epicwindmill.mvikotlindecomposekmmsample.android.ui.screens.RootUi
import com.epicwindmill.mvikotlindecomposekmmsample.android.ui.theme.NavigationSampleTheme
import com.epicwindmill.mvikotlindecomposekmmsample.api.SwansonQuotesApi
import com.epicwindmill.mvikotlindecomposekmmsample.components.root.IRoot
import com.epicwindmill.mvikotlindecomposekmmsample.components.root.RootComponent

class MainActivity : AppCompatActivity() {
    @ExperimentalDecomposeApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = myRoot(defaultComponentContext())

        setContent {
            NavigationSampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        RootUi(component)
                    }
                }
            }
        }
    }

    private fun myRoot(componentContext: ComponentContext): IRoot =
        RootComponent(
            componentContext = componentContext,
            storeFactory = LoggingStoreFactory(TimeTravelStoreFactory()),
            quotesApi = SwansonQuotesApi()
        )
}
