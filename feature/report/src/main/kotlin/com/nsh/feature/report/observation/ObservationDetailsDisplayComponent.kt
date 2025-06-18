package com.nsh.feature.report.observation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nsh.feature.report.R
import com.nsh.model.ObservationDetails

/**
 * Displays detailed information about a specific observation, including its name, value, unit,
 * reference range, and performer information if available. The function manages the layout and
 * styling of the displayed details, along with additional elements such as a reference range bar
 * for visual representation of the observation's value in relation to its defined thresholds.
 *
 * @param observationDetails The details of the observation including name, value, unit, reference
 * range (low and high), code display, and performer information.
 */
@Composable
fun ObservationDetailsDisplay(observationDetails: ObservationDetails) {
    val currentValue = observationDetails.value?.toFloatOrNull()
    val low = observationDetails.low
    val high = observationDetails.high

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = observationDetails.observationName,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(
                R.string.feature_report_description,
                observationDetails.codeDisplay.orEmpty().ifEmpty { "N/A" }),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        currentValue?.let {
            Text(
                text = stringResource(R.string.feature_report_value, it, observationDetails.unit.orEmpty()),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (low != null && high != null && currentValue != null) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(
                    R.string.feature_report_reference_range,
                    low,
                    high,
                    observationDetails.unit.orEmpty()
                ),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            ReferenceRangeBar(
                lowValue = low,
                highValue = high,
                currentValue = currentValue,
                unit = observationDetails.unit.orEmpty()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(
                R.string.feature_report_performed_by,
                observationDetails.performer.orEmpty().ifEmpty { stringResource(R.string.feature_report_n_a) }),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
