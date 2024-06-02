package com.example.data.remote.di

import com.example.data.remote.source.capsule.RemoteCapsuleDataSourceImpl
import com.example.data.remote.source.history.RemoteHistoryDataSourceImpl
import com.example.data.remote.source.launch.RemoteLaunchDataSourceImpl
import com.example.data.remote.source.mission.RemoteMissionDataSourceImpl
import com.example.data.remote.source.rocket.RemoteRocketDataSourceImpl
import com.example.data.remote.repo.capsule.RemoteCapsuleDataSource
import com.example.data.remote.repo.history.RemoteHistoryDataSource
import com.example.data.remote.repo.launch.RemoteLaunchDataSource
import com.example.data.remote.repo.mission.RemoteMissionDataSource
import com.example.data.remote.repo.rocket.RemoteRocketDataSource
import com.example.data.remote.repo.ship.RemoteShipDataSource
import com.example.data.remote.source.ship.RemoteShipDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindCapsuleInfoDataSource(datasource: RemoteCapsuleDataSourceImpl): RemoteCapsuleDataSource

    @Binds
    abstract fun bindHistoryInfoDataSource(datasource: RemoteHistoryDataSourceImpl): RemoteHistoryDataSource

    @Binds
    abstract fun bindMissionInfoDataSource(dataSource: RemoteMissionDataSourceImpl): RemoteMissionDataSource

    @Binds
    abstract fun bindRocketInfoDataSource(dataSource: RemoteRocketDataSourceImpl): RemoteRocketDataSource

    @Binds
    abstract fun bindLaunchInfoDataSource(dataSource: RemoteLaunchDataSourceImpl): RemoteLaunchDataSource

    @Binds
    abstract fun bindShipInfoDataSource(dataSource: RemoteShipDataSourceImpl): RemoteShipDataSource




}