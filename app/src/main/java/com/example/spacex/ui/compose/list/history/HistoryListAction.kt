package com.example.spacex.ui.compose.list.history

import com.example.common.state.UiAction

sealed class HistoryListAction: UiAction {

    data object Load: HistoryListAction()

    data class OnHistoryItemClick(
        val id: Int?
    ) : HistoryListAction()
}