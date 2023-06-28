package com.jeppung.rickymortywiki.common.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jeppung.rickymortywiki.common.Routes

@Composable
fun BottomNavigation(
    bottomNavState: Boolean,
    onMenuClick: (Routes) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    if(!bottomNavState) return

    val items = listOf(
        Routes.Characters,
        Routes.Locations,
        Routes.Episodes,
    )

    BottomAppBar() {
        items.forEachIndexed { i, route ->
            NavigationBarItem(
                selected = i == selectedIndex,
                onClick = {
                    onMenuClick(route)
                    selectedIndex = i
                },
                label = { Text(route.title) },
                icon = { Icon(imageVector = route.icon, contentDescription = null) }
            )
        }
    }
}