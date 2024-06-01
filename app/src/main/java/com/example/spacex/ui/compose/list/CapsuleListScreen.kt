package com.example.spacex.ui.compose.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
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
import com.example.spacex.model.CapsuleListModel
import com.example.spacex.ui.CapsuleListViewModel

@Composable
fun CapsuleListScreen(
    viewModel: CapsuleListViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(CapsuleListAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Column {
                CapsuleList(it) { item ->
                    viewModel.submitAction(
                        CapsuleListAction.OnCapsuleItemClick(
                            item.capsuleSerial
                        )
                    )
                }
            }
            
        }
    }

}

@Composable
fun CapsuleList(
    model: CapsuleListModel,
    onItemClick: (Capsule) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(model.items) { capsule ->
            CapsuleItem(capsule)
        }
    }

}


@Composable
fun CapsuleItem(capsule: Capsule) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Capsule ID: ${capsule.capsuleId}")
            Text(text = "Capsule Serial: ${capsule.capsuleSerial}")
            Text(text = "Details: ${capsule.details}")
            Text(text = "Landings: ${capsule.landings}")
            Text(text = "Original Launch: ${capsule.originalLaunch}")
            Text(text = "Reuse Count: ${capsule.reuseCount}")
            Text(text = "Status: ${capsule.status}")
            Text(text = "Type: ${capsule.type}")
        }
    }
}