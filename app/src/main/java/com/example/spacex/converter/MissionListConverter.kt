package com.example.spacex.converter

import android.content.Context
import com.example.common.state.CommonResultConverter
import com.example.domain.usecase.mission.GetMissionsUseCase
import com.example.spacex.model.Mission
import com.example.spacex.model.MissionListModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MissionListConverter @Inject constructor(
    @ApplicationContext private val context: Context
): CommonResultConverter<GetMissionsUseCase.Response, MissionListModel>() {
    override fun convertSuccess(data: GetMissionsUseCase.Response): MissionListModel {
        return data.missions.let { it ->
            MissionListModel(
                items = it!!.map {
                    Mission(
                        description = it?.description,
                        manufacturers = it?.manufacturers,
                        missionId = it?.missionId,
                        missionName = it?.missionName,
                        )
                }
            )
        }
    }


}