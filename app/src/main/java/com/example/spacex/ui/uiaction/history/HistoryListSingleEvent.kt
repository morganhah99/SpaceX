package com.example.spacex.ui.uiaction.history

import com.example.common.state.UiSingleEvent

sealed class HistoryListSingleEvent: UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String): HistoryListSingleEvent()
}