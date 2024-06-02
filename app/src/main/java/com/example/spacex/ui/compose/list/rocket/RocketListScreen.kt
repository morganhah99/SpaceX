package com.example.spacex.ui.compose.list.rocket

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
import androidx.navigation.NavController
import com.example.common.state.CommonScreen
import com.example.spacex.model.Mission
import com.example.spacex.model.MissionListModel
import com.example.spacex.model.Rocket
import com.example.spacex.model.RocketListModel
import com.example.spacex.ui.compose.list.mission.Mission
import com.example.spacex.ui.compose.list.mission.MissionList
import com.example.spacex.ui.compose.list.mission.MissionListAction
import com.example.spacex.ui.viewmodel.RocketListViewModel

@Composable
fun RocketListScreen(
    viewModel: RocketListViewModel,
    navController: NavController
) {

    LaunchedEffect(Unit) {
        viewModel.submitAction(RocketListAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Column {
                RocketList(it) { item ->
                    viewModel.submitAction(
                        RocketListAction.OnRocketItemClick(
                            item.id
                        )
                    )
                }
            }

        }
    }
}


@Composable
fun RocketList(
    model: RocketListModel,
    onItemClick: (Rocket) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(model.items) { rocket ->
            Rocket(rocket = rocket, onItemClick = onItemClick)
        }
    }

}




@Composable
fun Rocket(rocket: Rocket, onItemClick: (Rocket) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(rocket) }
        ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Rocket Name: ${rocket.rocketName}")
            Text(text = "Company: ${rocket.company}")
            Text(text = "Description: ${rocket.description}")
            Text(text = "Cost per launch: ${rocket.costPerLaunch}")
        }
    }
}