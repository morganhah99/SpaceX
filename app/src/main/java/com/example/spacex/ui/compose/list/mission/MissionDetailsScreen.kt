package com.example.spacex.ui.compose.list.mission

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.common.nav.input.MissionInput

@Composable
fun MissionDetailsScreen(missionInput: MissionInput) {

    Text(
        text = "Capsule Serial: ${missionInput.descriptions}",
        style = MaterialTheme.typography.h6
    )
}