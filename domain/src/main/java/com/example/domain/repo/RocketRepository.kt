package com.example.domain.repo

import com.example.domain.entity.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketRepository {

    fun getRockets(): Flow<List<Rocket>>

    fun getRocket(id: Int?): Flow<Rocket>
}