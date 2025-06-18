package com.nsh.feature.report

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nsh.feature.report.observation.ObservationDetailsDisplay
import com.nsh.model.DiagnosticReportDetails
import com.nsh.model.ObservationDetails

/**
 * Displays a card layout for a given diagnostic report, containing details such as the report name,
 * test performers, and the effective date/time.
 *
 * @param details The details of the diagnostic report, including report name, list of test performers,
 * and the report's effective date/time.
 */
@Composable
fun DiagnosticReportCard(details: DiagnosticReportDetails) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.feature_report_report_name, details.reportName),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.feature_report_test_performers, details.testPerformers.joinToString()),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.feature_report_effective_date, details.effectiveDateTime ?: "N/A"),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

