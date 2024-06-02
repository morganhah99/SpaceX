package com.example.spacex.ui.uiaction.ship

import com.example.common.state.UiSingleEvent

sealed class ShipListSingleEvent: UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String): ShipListSingleEvent()
}