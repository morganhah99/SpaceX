package com.example.data.repo.rocket

import com.example.domain.entity.Rocket
import kotlinx.coroutines.flow.Flow

interface RemoteRocketDataSource {

    fun getRockets(): Flow<List<Rocket>>

    fun getRocket(id: Int?): Flow<Rocket>
}