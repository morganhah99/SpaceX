package com.example.spacex.ui.uiaction.ship

import com.example.common.state.UiAction

sealed class ShipListAction: UiAction  {

    data object Load: ShipListAction()

    data class OnShipItemClick(
        val id: String?
    ) : ShipListAction()
}