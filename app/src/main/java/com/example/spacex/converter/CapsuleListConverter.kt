package com.example.spacex.converter

import android.content.Context
import com.example.common.state.CommonResultConverter
import com.example.domain.usecase.capsule.GetCapsulesUseCase
import com.example.spacex.model.Capsule
import com.example.spacex.model.CapsuleListModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CapsuleListConverter @Inject constructor(
    @ApplicationContext private val context: Context
) : CommonResultConverter<GetCapsulesUseCase.Response, CapsuleListModel>() {

    override fun convertSuccess(
        data: GetCapsulesUseCase.Response
    ): CapsuleListModel {
        return CapsuleListModel(
            items = data.capsules?.map {
                Capsule(
                    capsuleId = it?.capsuleId,
                    capsuleSerial = it?.capsuleSerial,
                    details = it?.details,
                    landings = it?.landings,
                    missions = it?.missions,
                    originalLaunch = it?.originalLaunch,
                    originalLaunchUnix = it?.originalLaunchUnix,
                    reuseCount = it?.reuseCount,
                    status = it?.status,
                    type = it?.type
                )
            } ?: listOf()
        )
    }
}