package com.example.spacex.ui.compose.list.history

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
import com.example.spacex.model.HistoryItem
import com.example.spacex.model.HistoryListModel
import com.example.spacex.ui.viewmodel.HistoryListViewModel

@Composable
fun HistoryListScreen(
    viewModel: HistoryListViewModel
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
                            item.id
                        )
                    )
                }

            }

        }
    }
}

@Composable
fun HistoryList(
    model: HistoryListModel,
    onItemClick: (HistoryItem) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(model.items) { history ->
            HistoryItem(history)
        }
    }
}

@Composable
fun HistoryItem(historyItem: HistoryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Title: ${historyItem.title}")
            Text(text = "Details: ${historyItem.details}")
            Text(text = "Event Date (UTC): ${historyItem.eventDateUtc}")
            Text(text = "Flight Number: ${historyItem.flightNumber}")
            Text(text = "ID: ${historyItem.id}")
        }

    }
}


