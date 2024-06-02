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
            text = "Capsule Serial: ${historyInput.details}",
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "Details: ${historyInput.date}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Status: ${historyInput.title}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Landings: ${historyInput.flightNumber}",
            style = MaterialTheme.typography.body1
        )
    }
}
