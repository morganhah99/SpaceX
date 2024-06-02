package com.example.data.remote.repo.rocket

import com.example.domain.entity.Rocket
import com.example.domain.repo.RocketRepository
import kotlinx.coroutines.flow.Flow

class RocketRepositoryImpl(
    private val remoteSource: RemoteRocketDataSource
) : RocketRepository {
    override fun getRockets(): Flow<List<Rocket>> {
        return remoteSource.getRockets()
    }

    override fun getRocket(id: Int?): Flow<Rocket> {
        return remoteSource.getRocket(id)
    }
}