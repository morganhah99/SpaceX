package com.example.spacex.ui.compose.list.history

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
import com.example.spacex.model.History
import com.example.spacex.model.HistoryListModel
import com.example.spacex.ui.uiaction.capsule.CapsuleListSingleEvent
import com.example.spacex.ui.uiaction.history.HistoryListAction
import com.example.spacex.ui.uiaction.history.HistoryListSingleEvent
import com.example.spacex.ui.viewmodel.HistoryListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HistoryListScreen(
    viewModel: HistoryListViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(HistoryListAction.Load)
    }


    viewModel.uiStateFlow.collectAsState().value.let{ state->
        CommonScreen(state = state) {
            Column {
                HistoryList(it) { item ->
                    viewModel.submitAction(
                        HistoryListAction.OnHistoryItemClick(
                            item.title,
                            item.details,
                            item.flightNumber,
                            item.eventDateUtc
                        )
                    )
                }

            }

        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is HistoryListSingleEvent.OpenDetailsScreen -> {
                    Log.i("ROUTE", it.navRoute)
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}

@Composable
fun HistoryList(
    model: HistoryListModel,
    onItemClick: (History) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(model.items) { history ->
            HistoryItem(historyItem = history, onItemClick = onItemClick)
        }
    }
}

@Composable
fun HistoryItem(historyItem: History, onItemClick: (History) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable { onItemClick (historyItem) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "${historyItem.title}")
        }

    }
}


