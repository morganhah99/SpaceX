package com.example.spacex.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.nav.routes.MissionInput
import com.example.common.nav.routes.MissionNavRoutes
import com.example.common.state.MviViewModel
import com.example.common.state.UiState
import com.example.domain.usecase.mission.GetMissionsUseCase
import com.example.spacex.converter.MissionListConverter
import com.example.spacex.model.MissionListModel
import com.example.spacex.ui.uiaction.mission.MissionListAction
import com.example.spacex.ui.uiaction.mission.MissionListSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionListViewModel @Inject constructor(
    private val useCase: GetMissionsUseCase,
    private val converter: MissionListConverter
) : MviViewModel<MissionListModel, UiState<MissionListModel>, MissionListAction, MissionListSingleEvent>() {
    override fun initState(): UiState<MissionListModel> = UiState.Loading

    override fun handleAction(action: MissionListAction) {
        when (action) {
            is MissionListAction.Load -> {
                loadMissions()
            }
            is MissionListAction.OnMissionItemClick -> {
                submitSingleEvent(
                    MissionListSingleEvent.OpenDetailsScreen(
                        MissionNavRoutes.Details.routeForMission(
                            MissionInput(
                               action.description
                            )
                        )
                    )
                )
            }
        }
    }

    private fun loadMissions() {
        viewModelScope.launch {
            useCase.execute(GetMissionsUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }


}