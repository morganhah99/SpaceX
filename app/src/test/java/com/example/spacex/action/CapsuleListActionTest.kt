package com.example.spacex.action

import com.example.spacex.ui.uiaction.capsule.CapsuleListAction
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CapsuleListActionTest {

    @Test
    fun `test Load action is singleton`() {
        val action1: CapsuleListAction = CapsuleListAction.Load
        val action2: CapsuleListAction = CapsuleListAction.Load
        assertTrue(action1 === action2)
    }

    @Test
    fun `test OnCapsuleItemClick action`() {
        val serial = "C101"
        val details = "Test capsule"
        val status = "active"
        val landings = 3
        val type = "Dragon 1.0"
        val launch = "2020-12-06"

        val action = CapsuleListAction.OnCapsuleItemClick(
            serial = serial,
            details = details,
            status = status,
            landings = landings,
            type = type,
            launch = launch
        )

        assertTrue(action is CapsuleListAction.OnCapsuleItemClick)
        assertEquals(serial, action.serial)
        assertEquals(details, action.details)
        assertEquals(status, action.status)
        assertEquals(landings, action.landings)
        assertEquals(type, action.type)
        assertEquals(launch, action.launch)
    }
}