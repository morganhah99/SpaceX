package com.example.data.remote.di

import com.example.data.remote.source.RemoteSpaceXDataSourceImpl
import com.example.data.repo.RemoteCapsuleDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindCompanyInfoDataSource(datasource: RemoteSpaceXDataSourceImpl): RemoteCapsuleDataSource
}