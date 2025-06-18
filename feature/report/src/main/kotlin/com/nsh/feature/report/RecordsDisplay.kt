package com.nsh.feature.report

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nsh.model.DiagnosticReportDetails

/**
 * A Composable function that displays diagnostic report details based on the state managed by the
 * provided [ReportViewModel]. Handles different UI states such as loading, success, and error.
 *
 * @param onReportClick A lambda function invoked when a diagnostic report is clicked. It takes a
 * nullable [DiagnosticReportDetails] object as a parameter.
 * @param reportViewModel The [ReportViewModel] managing the state of the diagnostic reports.
 * By default, it uses the Hilt-provided ViewModel instance.
 */
@Composable
fun RecordsDisplay(
    onReportClick: (DiagnosticReportDetails?) -> Unit,
    reportViewModel: ReportViewModel = hiltViewModel()
) {
    val reportUiState by reportViewModel.reportUiState.collectAsStateWithLifecycle()

    when (val state = reportUiState) {
        is ReportUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(size = DpSize(48.dp, 48.dp)))
            }
        }

        is ReportUiState.Success -> {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                onClick = { onReportClick(state.reportDetails) }
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalHospital,
                        contentDescription = stringResource(R.string.feature_report_lab_and_diagnostics_icon),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = stringResource(R.string.feature_report_lab_and_diagnostics),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.feature_report_results_for_blood_urine_and_general_lab),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        is ReportUiState.Error -> {
            Text((reportUiState as ReportUiState.Error).message)
        }
    }
}