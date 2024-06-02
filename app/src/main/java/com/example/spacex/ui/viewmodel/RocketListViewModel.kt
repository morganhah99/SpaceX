package com.example.spacex.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.nav.routes.MissionInput
import com.example.common.nav.routes.MissionNavRoutes
import com.example.common.nav.routes.RocketInput
import com.example.common.nav.routes.RocketNavRoutes
import com.example.common.state.MviViewModel
import com.example.common.state.UiState
import com.example.domain.usecase.rocket.GetRocketsUseCase
import com.example.spacex.converter.RocketListConverter
import com.example.spacex.model.RocketListModel
import com.example.spacex.ui.uiaction.mission.MissionListAction
import com.example.spacex.ui.uiaction.mission.MissionListSingleEvent
import com.example.spacex.ui.uiaction.rocket.RocketListAction
import com.example.spacex.ui.uiaction.rocket.RocketListSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketListViewModel @Inject constructor(
    private val useCase: GetRocketsUseCase,
    private val converter: RocketListConverter
) : MviViewModel<RocketListModel, UiState<RocketListModel>, RocketListAction, RocketListSingleEvent>(){


    override fun initState(): UiState<RocketListModel> = UiState.Loading


    override fun handleAction(action: RocketListAction) {
        when (action) {
            is RocketListAction.Load -> {
                loadRockets()
            }
            is RocketListAction.OnRocketItemClick -> {
                submitSingleEvent(
                    RocketListSingleEvent.OpenDetailsScreen(
                        RocketNavRoutes.Details.routeForRocket(
                            RocketInput(
                                action.company,
                                action.description,
                                action.costPerLaunch,
                                action.rocketType,
                                action.country,
                            )
                        )
                    )
                )
            }
        }
    }

    private fun loadRockets() {
        viewModelScope.launch {
            useCase.execute(GetRocketsUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect{
                    submitState(it)
                }
        }
    }


}