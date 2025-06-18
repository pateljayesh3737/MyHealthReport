package com.nsh.feature.report

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nsh.feature.report.observation.ObservationList
import com.nsh.model.DiagnosticReportDetails
import com.nsh.model.ObservationDetails

/**
 * Displays detailed information about a diagnostic report, including its observations and a clickable list
 * of observation items. The observations can trigger a specific action when clicked.
 *
 * @param diagnosticReportDetails The details of the diagnostic report, including its name, list of test performers,
 * effective date/time, and associated observations.
 * @param onObservationClick A lambda function that is invoked when an observation is clicked. It receives
 * an [ObservationDetails] object representing the clicked observation. Defaults to an empty lambda if no operation is specified.
 */
@Composable
fun ReportDetailsDisplay(
    diagnosticReportDetails: DiagnosticReportDetails,
    onObservationClick: (ObservationDetails) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DiagnosticReportCard(diagnosticReportDetails)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.feature_report_observations),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        ObservationList(
            observations = diagnosticReportDetails.observations,
            onObservationClick = {
                onObservationClick(it)
            }
        )
    }
}