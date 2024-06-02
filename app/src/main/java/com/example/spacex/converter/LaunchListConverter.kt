package com.example.spacex.converter

import android.content.Context
import com.example.common.state.CommonResultConverter
import com.example.domain.usecase.launch.GetLaunchesUseCase
import com.example.spacex.model.Launch
import com.example.spacex.model.LaunchListModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LaunchListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetLaunchesUseCase.Response, LaunchListModel>(){

    override fun convertSuccess(
        data: GetLaunchesUseCase.Response
    ): LaunchListModel {
        return LaunchListModel(
            items = data.launches!!.map {
                Launch(
                    details = it?.details,
                    flightNumber = it?.flightNumber,
                    launchSuccess = it?.launchSuccess,
                    launchYear = it?.launchYear,
                    missionName = it?.missionName,

                )
            }
        )
    }

}