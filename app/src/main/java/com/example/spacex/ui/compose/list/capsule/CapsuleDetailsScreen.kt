package com.example.spacex.ui.compose.list.capsule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.nav.routes.CapsuleInput

@Composable
fun CapsuleDetailsScreen(capsuleInput: CapsuleInput) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Details: ${capsuleInput.details}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Status: ${capsuleInput.status}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Landings: ${capsuleInput.landings}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Type: ${capsuleInput.type}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Launch: ${capsuleInput.launch}",
            style = MaterialTheme.typography.body1
        )
    }
}
