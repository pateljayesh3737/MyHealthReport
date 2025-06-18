package com.nsh.feature.report.observation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nsh.model.ObservationDetails

/**
 * Displays a list of observation items using a lazy column. Each item represents an observation and
 * is clickable to trigger an action.
 *
 * @param observations A list of observation details to be displayed in the list.
 * @param onObservationClick A lambda function to be executed when an observation item is clicked.
 * The clicked observation is passed as an argument to the lambda function.
 */
@Composable
fun ObservationList(
    observations: List<ObservationDetails>,
    onObservationClick: (ObservationDetails) -> Unit
) {
    LazyColumn {
        items(observations) { observation ->
            ObservationItem(observation, onObservationClick = { onObservationClick(observation) })
        }
    }
}

/**
 * Displays an observation item in a card format. The card is clickable, allowing the user to
 * perform an action upon selection. The displayed observation details include name, value,
 * and optionally reference ranges and performer information, depending on the data provided.
 *
 * @param observation The details of the observation to be displayed, such as name, value, unit,
 * reference range, and performer.
 * @param onObservationClick A lambda function that is triggered when the card is clicked. The
 * clicked observation details are passed as an argument to this function.
 */
@Composable
fun ObservationItem(
    observation: ObservationDetails,
    onObservationClick: (ObservationDetails) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = { onObservationClick(observation) }
    ) {
        ObservationDetailsDisplay(observation)
    }
}