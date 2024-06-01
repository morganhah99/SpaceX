package com.example.spacex.di

import com.example.data.repo.CapsuleRepositoryImpl
import com.example.data.repo.RemoteCapsuleDataSource
import com.example.domain.repo.CapsuleRepository
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
}