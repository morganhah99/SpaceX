package com.example.spacex.ui.compose.list.launch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.nav.input.LaunchInput

@Composable
fun LaunchDetailsScreen(launchInput: LaunchInput) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Capsule Serial: ${launchInput.details}",
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "Details: ${launchInput.missionName}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Status: ${launchInput.success}",
            style = MaterialTheme.typography.body1
        )
    }
}
