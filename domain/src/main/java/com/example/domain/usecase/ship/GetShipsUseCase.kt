package com.example.domain.usecase.ship

import com.example.domain.entity.Rocket
import com.example.domain.entity.Ship
import com.example.domain.repo.ShipRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShipsUseCase(
    configuration: Configuration,
    private val repo: ShipRepository
) : UseCase<GetShipsUseCase.Request, GetShipsUseCase.Response>(configuration){

    override fun process(request: Request): Flow<Response> =
        repo.getShips()
            .map {
                Response(it)
            }


    data object Request : UseCase.Request
    data class Response(val ships: List<Ship>) : UseCase.Response
}