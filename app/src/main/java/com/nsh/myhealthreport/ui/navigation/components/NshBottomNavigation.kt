package com.nsh.myhealthreport.ui.navigation.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.nsh.myhealthreport.TOP_LEVEL_ROUTES
import com.nsh.myhealthreport.ui.navigation.TopLevelBackStack

/**
 * A composable function that provides a bottom app bar implementation for the NSH application.
 * It displays navigation items corresponding to the top-level routes defined in the application
 * and facilitates navigation actions.
 *
 * @param topLevelBackStack An instance of [TopLevelBackStack] that manages the navigation stack
 * and provides the current top-level route for determining the selected navigation item.
 */
@Composable
fun NshBottomAppBar(topLevelBackStack: TopLevelBackStack<Any>) {
    BottomAppBar {
        TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
            val isSelected = topLevelRoute == topLevelBackStack.topLevelKey
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    topLevelBackStack.addTopLevel(topLevelRoute)
                },
                icon = {
                    Icon(
                        imageVector = topLevelRoute.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}