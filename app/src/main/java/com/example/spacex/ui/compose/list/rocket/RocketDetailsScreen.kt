package com.example.spacex.ui.compose.list.rocket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.nav.routes.RocketInput

@Composable
fun RocketDetailsScreen(rocketInput: RocketInput) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Company: ${rocketInput.company}",
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "Cost per Launch: $${rocketInput.costPerLaunch}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Description: ${rocketInput.description}",
            style = MaterialTheme.typography.body1
        )

    }
}
