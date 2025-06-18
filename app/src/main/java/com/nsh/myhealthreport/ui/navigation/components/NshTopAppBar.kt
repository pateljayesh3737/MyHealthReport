package com.nsh.myhealthreport.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.nsh.myhealthreport.ui.navigation.TopLevelBackStack

/**
 * A composable function that provides a top app bar implementation for the NSH application.
 * It dynamically updates its title and navigation behavior based on the passed top-level back stack.
 *
 * @param topLevelBackStack An instance of [TopLevelBackStack] that manages the navigation stack and
 * provides the top-level key for determining the current title and navigation actions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NshTopAppBar(topLevelBackStack: TopLevelBackStack<Any>) {
    TopAppBar(
        title = { Text(topLevelBackStack.topLevelKey.toString()) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            if (topLevelBackStack.backStack.size > 2) {
                IconButton(onClick = { topLevelBackStack.removeLast() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}