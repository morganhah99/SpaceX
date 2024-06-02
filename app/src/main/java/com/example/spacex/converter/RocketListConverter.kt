package com.example.spacex.converter

import android.content.Context
import com.example.common.state.CommonResultConverter
import com.example.domain.usecase.rocket.GetRocketsUseCase
import com.example.spacex.model.Rocket
import com.example.spacex.model.RocketListModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RocketListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetRocketsUseCase.Response, RocketListModel>() {
    override fun convertSuccess(data: GetRocketsUseCase.Response): RocketListModel {
        return RocketListModel(
            items = data.rockets!!.map {
                Rocket(
                    description = it?.description,
                    id = it?.id,
                    rocketName = it?.rocketName,
                    company = it?.company,
                    costPerLaunch = it?.costPerLaunch,
                    country = it?.country
                )
            }
        )
    }


}