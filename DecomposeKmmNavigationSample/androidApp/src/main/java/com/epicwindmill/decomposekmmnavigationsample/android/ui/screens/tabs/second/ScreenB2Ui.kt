package com.epicwindmill.decomposekmmnavigationsample.android.ui.screens.tabs.second

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.epicwindmill.decomposekmmnavigationsample.components.tabs.second.screenb2.IScreenB2

@Composable
fun ScreenB2Ui(component: IScreenB2) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Screen B2"
            )
        }
    }
}