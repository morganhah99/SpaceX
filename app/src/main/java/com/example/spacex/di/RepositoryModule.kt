package com.example.spacex.di

import com.example.data.remote.repo.capsule.CapsuleRepositoryImpl
import com.example.data.remote.repo.capsule.RemoteCapsuleDataSource
import com.example.data.remote.repo.history.HistoryRepositoryImpl
import com.example.data.remote.repo.history.RemoteHistoryDataSource
import com.example.data.remote.repo.launch.LaunchRepositoryImpl
import com.example.data.remote.repo.launch.RemoteLaunchDataSource
import com.example.data.remote.repo.mission.MissionRepositoryImpl
import com.example.data.remote.repo.mission.RemoteMissionDataSource
import com.example.data.remote.repo.rocket.RemoteRocketDataSource
import com.example.data.remote.repo.rocket.RocketRepositoryImpl
import com.example.data.remote.repo.ship.RemoteShipDataSource
import com.example.data.remote.repo.ship.ShipRepositoryImpl
import com.example.domain.repo.CapsuleRepository
import com.example.domain.repo.HistoryRepository
import com.example.domain.repo.LaunchRepository
import com.example.domain.repo.MissionRepository
import com.example.domain.repo.RocketRepository
import com.example.domain.repo.ShipRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideCapsuleRepository(
        remoteSource: RemoteCapsuleDataSource,
    ): CapsuleRepository = CapsuleRepositoryImpl(
        remoteSource
    )

    @Provides
    fun provideHistoryRepository(
        remoteSource: RemoteHistoryDataSource
    ): HistoryRepository = HistoryRepositoryImpl(
        remoteSource
    )

    @Provides
    fun provideMissionRepository(
        remoteSource: RemoteMissionDataSource
    ): MissionRepository = MissionRepositoryImpl(
        remoteSource
    )

    @Provides
    fun provideRocketRepository(
        remoteSource: RemoteRocketDataSource
    ) : RocketRepository = RocketRepositoryImpl(
        remoteSource
    )

    @Provides
    fun provideLaunchRepository(
        remoteSource: RemoteLaunchDataSource
    ) : LaunchRepository = LaunchRepositoryImpl(
        remoteSource
    )

    @Provides
    fun provideShipRepository(
        remoteSource: RemoteShipDataSource
    ) : ShipRepository = ShipRepositoryImpl (
        remoteSource
    )
}