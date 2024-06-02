package com.example.spacex.ui.compose.list.rocket

import com.example.common.state.UiSingleEvent

sealed class RocketListSingleEvent: UiSingleEvent {


    data class OpenDetailsScreen(val navRoute: String): RocketListSingleEvent()
}