package com.example.spacex.ui.compose.list.mission

import android.util.Log
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
import com.example.spacex.ui.uiaction.history.HistoryListSingleEvent
import com.example.spacex.ui.uiaction.mission.MissionListAction
import com.example.spacex.ui.uiaction.mission.MissionListSingleEvent
import com.example.spacex.ui.viewmodel.MissionListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MissionListScreen(
    viewModel: MissionListViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(MissionListAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Column {
                MissionList(it) { item ->
                    viewModel.submitAction(
                        MissionListAction.OnMissionItemClick(
                            item.description
                        )
                    )
                }
            }

        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is MissionListSingleEvent.OpenDetailsScreen -> {
                    Log.i("ROUTE", it.navRoute)
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}

@Composable
fun MissionList(
    model: MissionListModel,
    onItemClick: (Mission) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(model.items) { mission ->
            Mission(mission = mission, onItemClick = onItemClick)
        }
    }

}


@Composable
fun Mission(
    mission: Mission, onItemClick: (Mission) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(mission) }
        ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Mission Name: ${mission.missionName}")
        }
    }
}