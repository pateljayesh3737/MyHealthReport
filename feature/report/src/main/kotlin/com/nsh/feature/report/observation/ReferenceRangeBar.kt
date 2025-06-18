package com.nsh.feature.report.observation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Displays a reference range bar, visually indicating the position of a current value relative
 * to defined low and high thresholds. The bar is color-coded to depict whether the value is within
 * the normal range, below it, or above it.
 *
 * @param lowValue The lower threshold of the reference range.
 * @param highValue The upper threshold of the reference range.
 * @param currentValue The current value to be represented within the reference range.
 * @param unit The unit of measurement for the values being displayed.
 */
@Composable
fun ReferenceRangeBar(
    lowValue: Float,
    highValue: Float,
    currentValue: Float,
    unit: String,
) {
    val progress by animateFloatAsState(
        targetValue = ((currentValue - lowValue) / (highValue - lowValue)).coerceIn(0f, 1f)
    )

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color.LightGray
            ) {}
            Surface(
                modifier = Modifier
                    .fillMaxWidth(fraction = progress)
                    .height(8.dp),
                shape = RoundedCornerShape(4.dp),
                color = when {
                    currentValue < lowValue -> Color(0xFF006780) // Too low
                    currentValue > highValue -> Color(0xFFBA1A1A) // Too high
                    else -> Color(0xFF006D36) // Normal range
                }
            ) {}

        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Low: $lowValue $unit | High: $highValue $unit",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}