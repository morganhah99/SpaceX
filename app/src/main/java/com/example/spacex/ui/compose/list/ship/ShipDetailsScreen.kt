package com.example.spacex.ui.compose.list.ship

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.common.nav.routes.ShipInput

@Composable
fun ShipDetailsScreen(shipInput: ShipInput) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        AsyncImage(
            model = shipInput.image,
            contentDescription = "Ship Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Ship Name: ${shipInput.shipName}",
            style = MaterialTheme.typography.h6
        )

        Text(
            text = "Ship Type: ${shipInput.shipType}",
            style = MaterialTheme.typography.body1
        )

        Text(
            text = "Year Built: ${shipInput.yearBuilt}",
            style = MaterialTheme.typography.body1
        )
    }
}
