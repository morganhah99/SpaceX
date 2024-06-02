package com.example.spacex.ui.compose.list.mission

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.common.nav.routes.MissionInput

@Composable
fun MissionDetailsScreen(missionInput: MissionInput) {

    Column {
        Text(
            text = "Mission Description:",
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "${missionInput.descriptions}"
        )

    }

}