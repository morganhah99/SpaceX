package com.example.spacex.di.usecase.history

import com.example.domain.repo.HistoryRepository
import com.example.domain.usecase.UseCase
import com.example.domain.usecase.history.GetHistoryByIdUseCase
import com.example.domain.usecase.history.GetHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HistoryUseCaseModule {


    @Provides
    fun provideGetHistoryUseCase(
        configuration: UseCase.Configuration,
        repository: HistoryRepository
    ): GetHistoryUseCase = GetHistoryUseCase(
        configuration,
        repository
    )

    @Provides
    fun GetHistoryByIdUseCase(
        configuration: UseCase.Configuration,
        repository: HistoryRepository
    ): GetHistoryByIdUseCase = GetHistoryByIdUseCase(
        configuration,
        repository
    )
}