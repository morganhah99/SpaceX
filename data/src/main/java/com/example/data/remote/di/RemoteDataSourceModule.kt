package com.example.data.remote.di

import com.example.data.remote.source.capsule.RemoteCapsuleDataSourceImpl
import com.example.data.remote.source.history.RemoteHistoryDataSourceImpl
import com.example.data.repo.capsule.RemoteCapsuleDataSource
import com.example.data.repo.history.RemoteHistoryDataSource
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




}