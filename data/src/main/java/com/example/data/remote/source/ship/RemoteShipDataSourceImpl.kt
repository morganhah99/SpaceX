package com.example.data.remote.source.ship

import com.example.data.remote.model.ship.ShipItemModel
import com.example.data.remote.repo.ship.RemoteShipDataSource
import com.example.data.remote.service.SpaceXService
import com.example.domain.entity.Ship
import com.example.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteShipDataSourceImpl @Inject constructor(
    private val service: SpaceXService
) : RemoteShipDataSource {

    override fun getShips(): Flow<List<Ship>> = flow {
        val ships = service.getShips()
        emit(ships)
    }.map { shipList ->
        shipList.map { ship -> convert(ship) }
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    override fun getShip(id: String?): Flow<Ship> = flow {
        emit(service.getShip(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.SpaceXException(it)
    }

    private fun convert(model: ShipItemModel): Ship {
        return Ship(
            image = model.image,
            shipId = model.shipId,
            shipName = model.shipName,
            shipType = model.shipType,
            status = model.status,
            yearBuilt = model.yearBuilt,
            active = model.active
        )
    }
}