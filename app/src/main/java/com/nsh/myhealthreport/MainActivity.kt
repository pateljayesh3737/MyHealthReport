package com.nsh.myhealthreport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.nsh.model.DiagnosticReportDetails
import com.nsh.model.ObservationDetails
import com.nsh.myhealthreport.ui.navigation.NshApp
import com.nsh.myhealthreport.ui.navigation.TopLevelBackStack
import com.nsh.myhealthreport.ui.navigation.components.NshBottomAppBar
import com.nsh.myhealthreport.ui.navigation.components.NshTopAppBar
import com.nsh.myhealthreport.ui.theme.MyHealthReportTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val topLevelBackStack = remember { TopLevelBackStack<Any>(Home) }

            MyHealthReportTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { NshTopAppBar(topLevelBackStack) },
                    bottomBar = { NshBottomAppBar(topLevelBackStack) }
                ) { innerPadding ->
                    NshApp(modifier = Modifier.padding(innerPadding), backStack = topLevelBackStack)
                }
            }
        }
    }
}

sealed interface TopLevelRoute {
    val icon: ImageVector
    val title: String
}

data object Home : TopLevelRoute {
    override val icon = Icons.Default.Home
    override val title = "Home"
}

data object Records : TopLevelRoute {
    override val icon = Icons.Default.Dataset
    override val title = "Records"
}

data class ReportDetail(val diagnosticReportDetails: DiagnosticReportDetails, val pageTitle: String)
data class ObservationDetail(val observationDetails: ObservationDetails, val pageTitle: String)

val TOP_LEVEL_ROUTES: List<TopLevelRoute> = listOf(Home, Records)
