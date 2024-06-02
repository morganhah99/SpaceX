package com.example.spacex.converter

import android.content.Context
import com.example.common.state.CommonResultConverter
import com.example.domain.entity.Ship
import com.example.domain.usecase.ship.GetShipsUseCase
import com.example.spacex.model.ShipItem
import com.example.spacex.model.ShipListModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ShipListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetShipsUseCase.Response, ShipListModel>(){

    override fun convertSuccess(data: GetShipsUseCase.Response): ShipListModel {
        return ShipListModel(
            items = data.ships.map {
                ShipItem(
                    active = it.active,
                    image = it.image,
                    shipType = it.shipId,
                    shipName = it.shipName,
                    shipId = it.shipId,
                    status = it.status,
                    url = it.url,
                    weightLbs = it.weightLbs,
                    yearBuilt = it.yearBuilt
                )
            }
        )
    }
}