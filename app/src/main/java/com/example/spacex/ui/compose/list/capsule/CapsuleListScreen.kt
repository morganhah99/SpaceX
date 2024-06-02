package com.example.spacex.ui.compose.list.capsule

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
import com.example.spacex.model.Capsule
import com.example.spacex.model.CapsuleListModel
import com.example.spacex.ui.uiaction.capsule.CapsuleListAction
import com.example.spacex.ui.uiaction.capsule.CapsuleListSingleEvent
import com.example.spacex.ui.viewmodel.CapsuleListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CapsuleListScreen(
    viewModel: CapsuleListViewModel,
    navController: NavController
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
                            item.capsuleSerial,
                            item.details,
                            item.status,
                            item.landings,
                            item.type,
                            item.originalLaunch
                        )
                    )
                }
            }
            
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is CapsuleListSingleEvent.OpenDetailsScreen -> {
                    Log.i("ROUTE", it.navRoute)
                    navController.navigate(it.navRoute)
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
            CapsuleItem(capsule = capsule, onItemClick = onItemClick)
        }
    }

}


@Composable
fun CapsuleItem(capsule: Capsule, onItemClick: (Capsule) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(capsule) }
        ,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Capsule Serial: ${capsule.capsuleSerial}")
        }
    }
}