package com.example.domain.usecase.launch

import com.example.domain.entity.Launch
import com.example.domain.repo.LaunchRepository
import com.example.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLaunchByNumberUseCase(
    configuration: Configuration,
    private val repo: LaunchRepository
) : UseCase<GetLaunchByNumberUseCase.Request, GetLaunchByNumberUseCase.Response>(configuration) {


    data class Request(val number: Int?) : UseCase.Request
    data class Response(val launchItem: Launch) : UseCase.Response

    override fun process(request: Request): Flow<Response> =
        repo.getLaunchItem(request.number)
            .map {
                Response(it)
            }


}