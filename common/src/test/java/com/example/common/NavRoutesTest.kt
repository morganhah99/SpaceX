package com.example.common

import com.example.common.nav.NavRoutes
import com.example.common.nav.routes.CapsuleNavRoutes
import com.example.common.nav.routes.HistoryNavRoutes
import com.example.common.nav.routes.LaunchNavRoutes
import com.example.common.nav.routes.MissionNavRoutes
import com.example.common.nav.routes.RocketNavRoutes
import com.example.common.nav.routes.ShipNavRoutes
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class NavRoutesTest {

    @Test
    fun `test Home route is correctly defined`() {
        assertEquals("home", NavRoutes.Home.route)
        assertTrue(NavRoutes.Home.arguments.isEmpty())
    }

    @Test
    fun `test Capsule route is correctly defined`() {
        assertEquals(CapsuleNavRoutes.Details.route, NavRoutes.Capsule.route)
        assertEquals(CapsuleNavRoutes.Details.arguments, NavRoutes.Capsule.arguments)
    }

    @Test
    fun `test History route is correctly defined`() {
        assertEquals(HistoryNavRoutes.Details.route, NavRoutes.History.route)
        assertEquals(HistoryNavRoutes.Details.arguments, NavRoutes.History.arguments)
    }

    @Test
    fun `test Launch route is correctly defined`() {
        assertEquals(LaunchNavRoutes.Details.route, NavRoutes.Launch.route)
        assertEquals(LaunchNavRoutes.Details.arguments, NavRoutes.Launch.arguments)
    }

    @Test
    fun `test Mission route is correctly defined`() {
        assertEquals(MissionNavRoutes.Details.route, NavRoutes.Mission.route)
        assertEquals(MissionNavRoutes.Details.arguments, NavRoutes.Mission.arguments)
    }

    @Test
    fun `test Rocket route is correctly defined`() {
        assertEquals(RocketNavRoutes.Details.route, NavRoutes.Rocket.route)
        assertEquals(RocketNavRoutes.Details.arguments, NavRoutes.Rocket.arguments)
    }

    @Test
    fun `test Ship route is correctly defined`() {
        assertEquals(ShipNavRoutes.Details.route, NavRoutes.Ship.route)
        assertEquals(ShipNavRoutes.Details.arguments, NavRoutes.Ship.arguments)
    }
}