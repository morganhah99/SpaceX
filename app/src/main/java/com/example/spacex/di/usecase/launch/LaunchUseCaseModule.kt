package com.example.spacex.di.usecase.launch

import com.example.domain.repo.LaunchRepository
import com.example.domain.usecase.UseCase
import com.example.domain.usecase.launch.GetLaunchByNumberUseCase
import com.example.domain.usecase.launch.GetLaunchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class LaunchUseCaseModule {

    @Provides
    fun provideGetLaunchesUseCase(
        configuration: UseCase.Configuration,
        repository: LaunchRepository
    ): GetLaunchesUseCase = GetLaunchesUseCase(
        configuration,
        repository
    )

    @Provides
    fun provideGetLaunchByNumberUseCase(
        configuration: UseCase.Configuration,
        repository: LaunchRepository
    ) : GetLaunchByNumberUseCase = GetLaunchByNumberUseCase(
        configuration,
        repository
    )
}