package com.example.common.nav

import com.google.gson.Gson

data class CapsuleInput (
    val serialId: String?,
    val details: String?,
    val status: String?,
    val landings: Int?,
    val type: String?,
    val launch: String?
) {

    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): CapsuleInput {
            return Gson().fromJson(json, CapsuleInput::class.java)
        }
    }

}

