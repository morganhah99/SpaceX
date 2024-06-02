package com.example.domain.repo

import com.example.domain.entity.Ship
import kotlinx.coroutines.flow.Flow

interface ShipRepository {

    fun getShips(): Flow<List<Ship>>

    fun getShip(id: String?): Flow<Ship>

}