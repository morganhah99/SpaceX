package com.example.spacex.ui.uiaction.launch

import com.example.common.state.UiSingleEvent

sealed class LaunchListSingleEvent: UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String): LaunchListSingleEvent()
}