package com.example.spacex.ui.uiaction.capsule

import com.example.common.state.UiSingleEvent

sealed class CapsuleListSingleEvent : UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String) : CapsuleListSingleEvent()
}