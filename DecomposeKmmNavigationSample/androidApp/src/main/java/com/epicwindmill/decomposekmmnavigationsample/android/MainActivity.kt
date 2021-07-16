package com.epicwindmill.decomposekmmnavigationsample.android

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
import com.arkivanov.decompose.extensions.compose.jetpack.rememberRootComponent
import com.epicwindmill.decomposekmmnavigationsample.Greeting
import com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.RootUi
import com.epicwindmill.decomposekmmnavigationsample.android.ui.theme.NavigationSampleTheme
import com.epicwindmill.decomposekmmnavigationsample.components.root.RootComponent

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        val component = rememberRootComponent(::RootComponent)
                        RootUi(component)
                    }
                }
            }
        }
    }
}
