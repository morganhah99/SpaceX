package com.example.spacex.ui.uiaction.rocket

import com.example.common.state.UiAction

sealed class RocketListAction : UiAction {

    data object Load : RocketListAction()

    data class OnRocketItemClick(
        val company: String?,
        val description: String?,
        val costPerLaunch: Int?,
        val rocketType: String?,
        val country: String?
    ) : RocketListAction()
}