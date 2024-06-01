package com.example.spacex.ui.compose.list.capsule

import com.example.common.state.UiSingleEvent

sealed class CapsuleListSingleEvent : UiSingleEvent {

    data class OpenDetailsScreen(val navRoute: String) : CapsuleListSingleEvent()
}