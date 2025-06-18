package com.nsh.myhealthreport.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.nsh.feature.home.HomeDisplay
import com.nsh.feature.report.RecordsDisplay
import com.nsh.feature.report.ReportDetailsDisplay
import com.nsh.feature.report.observation.ObservationDetailsDisplay
import com.nsh.myhealthreport.Home
import com.nsh.myhealthreport.ObservationDetail
import com.nsh.myhealthreport.Records
import com.nsh.myhealthreport.ReportDetail

/**
 * A composable function that serves as the main entry point for the NSH application UI.
 * It manages navigation through different screens and renders the appropriate content
 * based on the current back stack state.
 *
 * @param modifier A [Modifier] to be applied to the overall composable for layout and styling purposes.
 * @param backStack A [TopLevelBackStack] instance that handles navigation and manages the stack of screens
 * displayed in the application. The back stack controls the order of displayed screens and
 * facilitates navigation actions such as adding or removing screens.
 */
@Composable
fun NshApp(
    modifier: Modifier,
    backStack: TopLevelBackStack<Any>,
) {
    NavDisplay(
        modifier = modifier,
        backStack = backStack.backStack,
        onBack = { backStack.removeLast() },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<Home> { HomeDisplay(modifier = modifier) }

            entry<Records> {
                RecordsDisplay(
                    onReportClick = { it ->
                        it?.let { diagnosticReportDetails ->
                            backStack.add(
                                ReportDetail(
                                    diagnosticReportDetails,
                                    diagnosticReportDetails.reportName
                                )
                            )
                        }
                    }
                )
            }

            entry<ReportDetail> { entry ->
                ReportDetailsDisplay(
                    entry.diagnosticReportDetails,
                    onObservationClick = {
                        backStack.add(ObservationDetail(it, entry.pageTitle))
                    }
                )
            }

            entry<ObservationDetail> { entry ->
                OutlinedCard(modifier = Modifier.padding(16.dp)) {
                    ObservationDetailsDisplay(entry.observationDetails)
                }
            }
        }
    )
}