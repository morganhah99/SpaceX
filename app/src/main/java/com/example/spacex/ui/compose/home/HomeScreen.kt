package com.example.spacex.ui.compose.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.common.nav.NavRoutes

@Composable
fun HomeScreen(navController: NavHostController) {
    val items = listOf(
        "Capsules" to NavRoutes.ROUTE_CAPSULES,
        "History" to NavRoutes.ROUTE_HISTORY,
        "Launches" to NavRoutes.ROUTE_LAUNCHES,
        "Missions" to NavRoutes.ROUTE_MISSIONS,
        "Rockets" to NavRoutes.ROUTE_ROCKETS,
        "Ships" to NavRoutes.ROUTE_SHIPS
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
                items(items) { (name, route) ->
                    HomeGridItem(name = name) {
                        if (route.isNotEmpty()) {
                            navController.navigate(route)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun HomeGridItem(name: String, onClick:() -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(8.dp)
            .clickable { onClick() },

        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = name, style = MaterialTheme.typography.h6)
        }
    }
}