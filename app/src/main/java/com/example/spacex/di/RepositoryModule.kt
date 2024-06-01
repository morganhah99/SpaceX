package com.example.spacex.di

import com.example.data.repo.capsule.CapsuleRepositoryImpl
import com.example.data.repo.capsule.RemoteCapsuleDataSource
import com.example.data.repo.history.HistoryRepositoryImpl
import com.example.data.repo.history.RemoteHistoryDataSource
import com.example.domain.repo.CapsuleRepository
import com.example.domain.repo.HistoryRepository
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
}