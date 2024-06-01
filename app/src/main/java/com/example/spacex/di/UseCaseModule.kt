package com.example.spacex.di

import com.example.domain.repo.CapsuleRepository
import com.example.domain.usecase.GetCapsuleBySerialUseCase
import com.example.domain.usecase.GetCapsulesUseCase
import com.example.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetMoviesUseCase(
        configuration: UseCase.Configuration,
        repository: CapsuleRepository
    ): GetCapsulesUseCase = GetCapsulesUseCase(
        configuration,
        repository
    )

    @Provides
    fun GetCapsuleBySerialUseCase(
        configuration: UseCase.Configuration,
        repository: CapsuleRepository
    ): GetCapsuleBySerialUseCase = GetCapsuleBySerialUseCase(
        configuration,
        repository
    )


}