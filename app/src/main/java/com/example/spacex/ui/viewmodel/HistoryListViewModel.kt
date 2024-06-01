package com.example.spacex.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.state.MviViewModel
import com.example.common.state.UiState
import com.example.domain.usecase.history.GetHistoryUseCase
import com.example.spacex.converter.HistoryListConverter
import com.example.spacex.model.HistoryListModel
import com.example.spacex.ui.compose.list.history.HistoryListAction
import com.example.spacex.ui.compose.list.history.HistoryListSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryListViewModel @Inject constructor(
    private val useCase: GetHistoryUseCase,
    private val converter: HistoryListConverter
): MviViewModel<HistoryListModel, UiState<HistoryListModel>, HistoryListAction, HistoryListSingleEvent>() {
    override fun initState(): UiState<HistoryListModel> = UiState.Loading

    override fun handleAction(action: HistoryListAction) {
        when (action) {
            is HistoryListAction.Load -> {
                loadHistory()
            }
            is HistoryListAction.OnHistoryItemClick ->  TODO()
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            useCase.execute(GetHistoryUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }


}