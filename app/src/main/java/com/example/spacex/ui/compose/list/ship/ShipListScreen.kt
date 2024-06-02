package com.example.spacex.ui.compose.list.ship

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.state.CommonScreen
import com.example.spacex.model.Capsule
import com.example.spacex.model.Rocket
import com.example.spacex.model.RocketListModel
import com.example.spacex.model.ShipItem
import com.example.spacex.model.ShipListModel
import com.example.spacex.ui.compose.list.launch.LaunchList
import com.example.spacex.ui.compose.list.rocket.Rocket
import com.example.spacex.ui.uiaction.launch.LaunchListAction
import com.example.spacex.ui.uiaction.ship.ShipListAction
import com.example.spacex.ui.viewmodel.ShipListViewModel


@Composable
fun ShipListScreen(
    viewModel: ShipListViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(ShipListAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let{ state->
        CommonScreen(state = state) {
            Column {
                ShipList(it) { item ->
                    viewModel.submitAction(
                        ShipListAction.OnShipItemClick(
                            item.shipId
                        )
                    )
                }

            }

        }
    }
}



@Composable
fun ShipList(
    model: ShipListModel,
    onItemClick: (ShipItem) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(model.items) { ship ->
            ShipItem(ship)
        }
    }

}






@Composable
fun ShipItem(ship: ShipItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()

        ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Capsule Serial: ${ship.shipId}")
            Text(text = "Details: ${ship.shipModel}")
            Text(text = "Landings: ${ship.shipName}")
            Text(text = "Original Launch: ${ship.status}")
            Text(text = "Status: ${ship.shipType}")
            Text(text = "Type: ${ship.active}")
            Text(text = "Type: ${ship.image}")
            Text(text = "Type: ${ship.weightLbs}")
            Text(text = "Type: ${ship.yearBuilt}")

        }
    }
}