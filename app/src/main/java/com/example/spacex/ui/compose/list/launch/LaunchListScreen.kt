package com.example.spacex.ui.compose.list.launch

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
import androidx.navigation.NavHostController
import com.example.common.state.CommonScreen
import com.example.spacex.model.Launch
import com.example.spacex.model.LaunchListModel
import com.example.spacex.ui.compose.list.history.HistoryList
import com.example.spacex.ui.uiaction.history.HistoryListAction
import com.example.spacex.ui.uiaction.history.HistoryListSingleEvent
import com.example.spacex.ui.uiaction.launch.LaunchListAction
import com.example.spacex.ui.uiaction.launch.LaunchListSingleEvent
import com.example.spacex.ui.viewmodel.LaunchListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LaunchListScreen(
    viewModel: LaunchListViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(LaunchListAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let{ state->
        CommonScreen(state = state) {
            Column {
                LaunchList(it) { item ->
                    viewModel.submitAction(
                        LaunchListAction.OnLaunchItemClick(
                            item.details,
                            item.launchSuccess,
                            item.missionName,
                            item.launchYear
                        )
                    )
                }

                LaunchedEffect(Unit) {
                    viewModel.singleEventFlow.collectLatest {
                        when (it) {
                            is LaunchListSingleEvent.OpenDetailsScreen -> {
                                Log.i("ROUTE", it.navRoute)
                                navController.navigate(it.navRoute)
                            }
                        }
                    }
                }

            }

        }
    }
}

@Composable
fun LaunchList(
    model: LaunchListModel,
    onItemClick: (Launch) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(model.items) { launch ->
            LaunchItem(launchItem = launch, onItemClick = onItemClick)
        }
    }

}




@Composable
fun LaunchItem(launchItem: Launch, onItemClick: (Launch) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable { onItemClick (launchItem) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Flight Number: ${launchItem.flightNumber}")
        }

    }
}
