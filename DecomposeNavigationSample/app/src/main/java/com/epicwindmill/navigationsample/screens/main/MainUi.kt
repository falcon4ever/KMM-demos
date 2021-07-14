package com.epicwindmill.navigationsample.screens.main

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetpack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.animation.child.crossfade
import com.arkivanov.decompose.extensions.compose.jetpack.asState
import com.epicwindmill.navigationsample.R
import com.epicwindmill.navigationsample.screens.tabs.first.screena1.ScreenA1Ui
import com.epicwindmill.navigationsample.screens.tabs.second.screenb1.ScreenB1Ui
import com.epicwindmill.navigationsample.screens.tabs.third.screenc1.ScreenC1Ui

@ExperimentalComposeUiApi
@Composable
fun MainUi(main: IMain) {
    val model by main.model.asState()
    Scaffold(
        topBar = { TopBar(
            title = model.selectedTab.name.lowercase().capitalize() + " Tab"
        ) },
        bottomBar = { BottomNavigationBar(
            selectedTab = model.selectedTab,
            onClick = main::onTabClick
        ) }
    ) {
        Children(
            routerState = main.routerState,
            animation = crossfade()
        ) {
            Log.d("MainUi", "nav: {${it.instance.toString()}}")
            when (val child = it.instance) {
                is IMain.Child.FirstTab -> ScreenA1Ui(child.component)
                is IMain.Child.SecondTab -> ScreenB1Ui(child.component)
                is IMain.Child.ThirdTab -> ScreenC1Ui(child.component)
            }.let {}
        }
    }
}

@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = 18.sp)
        }
    )
}

@Composable
fun BottomNavigationBar(
    selectedTab: IMain.Tab,
    onClick: (IMain.Tab) -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(painterResource(id = R.drawable.ic_home), contentDescription = "first tab")
                   },
            label = { Text(text = "First") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.FIRST,
            onClick = {
                onClick(IMain.Tab.FIRST)
            }
        )
        BottomNavigationItem(
            icon = { Icon(
                painterResource(id = R.drawable.ic_list), contentDescription = "second tab") },
            label = { Text(text = "Second") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.SECOND,
            onClick = {
                onClick(IMain.Tab.SECOND)
            }
        )
        BottomNavigationItem(
            icon = { Icon(
                painterResource(id = R.drawable.ic_feedback), contentDescription = "third tab"
            ) },
            label = { Text(text = "Third") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true,
            selected = selectedTab == IMain.Tab.THIRD,
            onClick = {
                onClick(IMain.Tab.THIRD)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("Test title")
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(IMain.Tab.FIRST, {})
}