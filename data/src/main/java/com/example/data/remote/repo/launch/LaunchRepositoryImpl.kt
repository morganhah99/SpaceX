package com.example.data.remote.repo.launch

import com.example.domain.entity.Launch
import com.example.domain.repo.LaunchRepository
import kotlinx.coroutines.flow.Flow

class LaunchRepositoryImpl(
    private val remoteSource: RemoteLaunchDataSource
) : LaunchRepository{

    override fun getLaunches(): Flow<List<Launch>> {
        return remoteSource.getLaunches()
    }

    override fun getLaunchItem(number: Int?): Flow<Launch> {
        return remoteSource.getLaunch(number)
    }
}