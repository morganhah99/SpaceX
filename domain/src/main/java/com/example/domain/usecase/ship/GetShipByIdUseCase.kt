package com.example.domain.usecase.ship

import com.example.domain.entity.Ship
import com.example.domain.repo.ShipRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShipByIdUseCase(
    configuration: Configuration,
    private val repo: ShipRepository
) : UseCase<GetShipByIdUseCase.Request, GetShipByIdUseCase.Response>(configuration){


    override fun process(request: Request): Flow<Response> =
        repo.getShip(request.id)
            .map {
                Response(it)
            }


    data class Request(val id: String?) : UseCase.Request
    data class Response(val ship: Ship?) : UseCase.Response
}