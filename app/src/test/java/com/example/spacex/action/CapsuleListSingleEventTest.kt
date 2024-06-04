package com.example.spacex.action

import com.example.spacex.ui.uiaction.capsule.CapsuleListSingleEvent
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CapsuleListSingleEventTest {

    @Test
    fun `test OpenDetailsScreen instantiation`() {
        val navRoute = "details/1"
        val event = CapsuleListSingleEvent.OpenDetailsScreen(navRoute)

        assertTrue(event is CapsuleListSingleEvent.OpenDetailsScreen)
        assertEquals(navRoute, event.navRoute)
    }
}