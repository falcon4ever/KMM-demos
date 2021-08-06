package com.epicwindmill.mvikotlindecomposekmmsample.android.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.epicwindmill.mvikotlindecomposekmmsample.android.R
import com.epicwindmill.mvikotlindecomposekmmsample.components.main.IMain

@ExperimentalDecomposeApi
@ExperimentalComposeUiApi
@Composable
fun MainUi(component: IMain) {
    val model by component.models.subscribeAsState()

    MainContent(quote = model.quote,
        buttonClick = {
            component.fetchRandomQuote()
        })
}

@Composable
fun MainContent(quote: String, buttonClick: () -> Unit) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = buttonClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_refresh_24),
                    contentDescription = "Refresh quote"
                )
            }
        },
        content = {
            Column {
                Spacer(modifier = Modifier.size(64.dp))
                Image(
                    painter = painterResource(R.drawable.ron_swanson),
                    contentDescription = null,
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.size(32.dp))
                Text(
                    text = "\"$quote\"",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(32.dp)
                        .fillMaxWidth()
                )
            }
        },
    )
}

@Preview
@Composable
fun MainUiPreview() {
    MainContent(quote = "Hello World") { }
}