package com.example.spacex.ui.compose.list.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.nav.routes.HistoryInput

@Composable
fun HistoryDetailsScreen(historyInput: HistoryInput) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Event Title: ${historyInput.title}",
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "Event Details: ${historyInput.details}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Flight Number: ${historyInput.flightNumber}",
            style = MaterialTheme.typography.body1
        )

    }
}
