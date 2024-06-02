package com.example.data.remote.repo.ship

import com.example.domain.entity.Ship
import kotlinx.coroutines.flow.Flow

interface RemoteShipDataSource {

    fun getShips(): Flow<List<Ship>>

    fun getShip(id: String?): Flow<Ship>
}