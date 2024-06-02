package com.example.data.remote.repo.ship

import com.example.domain.entity.Ship
import com.example.domain.repo.ShipRepository
import kotlinx.coroutines.flow.Flow

class ShipRepositoryImpl(
    private val remoteSource: RemoteShipDataSource
) : ShipRepository {
    override fun getShips(): Flow<List<Ship>> {
        return remoteSource.getShips()
    }

    override fun getShip(id: String?): Flow<Ship> {
        return remoteSource.getShip(id)
    }
}