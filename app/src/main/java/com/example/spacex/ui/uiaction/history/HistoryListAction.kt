package com.example.spacex.ui.uiaction.history

import com.example.common.state.UiAction

sealed class HistoryListAction: UiAction {

    data object Load: HistoryListAction()

    data class OnHistoryItemClick(
        val title: String?,
        val details: String?,
        val flightNumber: Int?,
        val date: String?
    ) : HistoryListAction()
}