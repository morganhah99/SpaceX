package com.example.spacex.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.common.nav.routes.RocketInput
import com.example.common.nav.routes.RocketNavRoutes
import com.example.common.nav.routes.ShipInput
import com.example.common.nav.routes.ShipNavRoutes
import com.example.common.state.MviViewModel
import com.example.common.state.UiState
import com.example.domain.usecase.ship.GetShipsUseCase
import com.example.spacex.converter.ShipListConverter
import com.example.spacex.model.HistoryListModel
import com.example.spacex.model.ShipListModel
import com.example.spacex.ui.uiaction.history.HistoryListAction
import com.example.spacex.ui.uiaction.rocket.RocketListAction
import com.example.spacex.ui.uiaction.rocket.RocketListSingleEvent
import com.example.spacex.ui.uiaction.ship.ShipListAction
import com.example.spacex.ui.uiaction.ship.ShipListSingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShipListViewModel @Inject constructor(
    private val useCase: GetShipsUseCase,
    private val converter: ShipListConverter
) : MviViewModel<ShipListModel, UiState<ShipListModel>, ShipListAction, ShipListSingleEvent>(){

    override fun initState(): UiState<ShipListModel> = UiState.Loading


    override fun handleAction(action: ShipListAction) {
        when (action) {
            is ShipListAction.Load -> {
                loadShips()
            }
            is ShipListAction.OnShipItemClick -> {
                submitSingleEvent(
                    ShipListSingleEvent.OpenDetailsScreen(
                        ShipNavRoutes.Details.routeForShip(
                            ShipInput(
                                action.model,
                                action.shipName,
                                action.status,
                                action.shipType,
                                action.image,
                                action.weight,
                                action.yearBuilt
                            )
                        )
                    )
                )
            }
        }
    }

    private fun loadShips(){
        viewModelScope.launch {
            useCase.execute(GetShipsUseCase.Request)
                .map {
                    converter.convert(it)
                }
                .collect{
                    submitState(it)
                }
        }
    }
}