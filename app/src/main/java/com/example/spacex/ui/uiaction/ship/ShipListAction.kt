package com.example.spacex.ui.uiaction.ship

import com.example.common.state.UiAction

sealed class ShipListAction: UiAction  {

    data object Load: ShipListAction()

    data class OnShipItemClick(
        val model: String?,
        val shipName: String?,
        val status: String?,
        val shipType: String?,
        val image: String?,
        val weight: Int?,
        val yearBuilt: Int?
    ) : ShipListAction()
}