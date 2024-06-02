package com.example.spacex.ui.compose.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.common.nav.NavRoutes
import com.example.spacex.R
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val items = listOf(
        Triple("Capsules", NavRoutes.ROUTE_CAPSULES, R.drawable.capsule),
        Triple("History", NavRoutes.ROUTE_HISTORY, R.drawable.history),
        Triple("Launches", NavRoutes.ROUTE_LAUNCHES, R.drawable.launch),
        Triple("Missions", NavRoutes.ROUTE_MISSIONS, R.drawable.mission),
        Triple("Rockets", NavRoutes.ROUTE_ROCKETS, R.drawable.rocket),
        Triple("Ships", NavRoutes.ROUTE_SHIPS, R.drawable.ship)
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "SpaceX Dashboard",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 50.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items) { item ->
                    HomeGridItem(name = item.first, imageRes = item.third) {
                        if (item.second.isNotEmpty()) {
                            navController.navigate(item.second)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeGridItem(name: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = name, style = MaterialTheme.typography.h6, color = Color.White)
            }
        }
    }
}
