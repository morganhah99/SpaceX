package com.example.spacex.di.usecase.capsule

import com.example.domain.repo.CapsuleRepository
import com.example.domain.usecase.capsule.GetCapsuleBySerialUseCase
import com.example.domain.usecase.capsule.GetCapsulesUseCase
import com.example.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class CapsuleUseCaseModule {


    @Provides
    fun provideGetCapsulesUseCase(
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