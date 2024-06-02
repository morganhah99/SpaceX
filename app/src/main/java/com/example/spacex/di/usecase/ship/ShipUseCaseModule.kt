package com.example.spacex.di.usecase.ship

import com.example.domain.repo.ShipRepository
import com.example.domain.usecase.UseCase
import com.example.domain.usecase.ship.GetShipByIdUseCase
import com.example.domain.usecase.ship.GetShipsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ShipUseCaseModule {

    @Provides
    fun provideGetShipsUseCase(
        configuration: UseCase.Configuration,
        repository: ShipRepository
    ) : GetShipsUseCase = GetShipsUseCase(
        configuration,
        repository
    )

    @Provides
    fun provideGetShipByIdUseCase(
        configuration: UseCase.Configuration,
        repository: ShipRepository
    ) : GetShipByIdUseCase = GetShipByIdUseCase(
        configuration,
        repository
    )
}