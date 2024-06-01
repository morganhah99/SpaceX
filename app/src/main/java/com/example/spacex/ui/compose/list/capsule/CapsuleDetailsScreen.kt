package com.example.spacex.ui.compose.list.capsule

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.common.nav.CapsuleInput

@Composable
fun CapsuleDetailsScreen(capsuleInput: CapsuleInput) {
    Text(
        text = "Capsule Serial: $capsuleInput"
    )
}