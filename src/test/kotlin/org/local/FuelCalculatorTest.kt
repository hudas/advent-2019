package org.local

import org.junit.Test
import kotlin.test.assertEquals

class FuelCalculatorTest {

    @Test
    fun shouldCalculateFuelRequiredForDivisableItem() {
        val fleet = Fleet(listOf(12))
        assertEquals(12 / 3 - 2, fleet.requiredFuelForModules())
    }

    @Test
    fun shouldCalculateFuelRequiredForNonDivisableItems() {
        val firstFleet = Fleet(listOf(14))
        assertEquals(14 / 3 - 2, firstFleet.requiredFuelForModules())

        val secondFleet = Fleet(listOf(1969))
        assertEquals(1969 / 3 - 2, secondFleet.requiredFuelForModules())
    }

    @Test
    fun shouldCalculateFuelRequiredForFleetOfMultipleModules() {
        val fleet = Fleet(listOf(12, 14, 1969))
        assertEquals(2 + 2 + 654, fleet.requiredFuelForModules())
    }


    @Test
    fun shouldNotAdjustFuelRequiredIfFuelMassCarriageRequiresZeroFuel() {
        val fleet = Fleet(listOf(12))
        assertEquals(12 / 3 - 2, fleet.requiredTotalFuel())

        val secondFleet = Fleet(listOf(32))
        assertEquals(30 / 3 - 2, secondFleet.requiredTotalFuel())
    }

    @Test
    fun shouldIncludeAddedFuelMassWhenCalculatingTotalFuelRequired() {
        val firstFleet = Fleet(listOf(33))
        assertEquals((33 / 3 - 2) + (33 / 3 - 2) / 3 - 2, firstFleet.requiredTotalFuel())

        val secondFleet = Fleet(listOf(1969))
        assertEquals(966, secondFleet.requiredTotalFuel(), "Does not include added fuel continuously")

        val thirdFleet = Fleet(listOf(100756))
        assertEquals(50346, thirdFleet.requiredTotalFuel(), "Does not include added fuel continuously")
    }

    @Test
    fun shouldAdjustFuelForModulesInFleetSeparatelyBeforeGettingTotal() {
        val fleet = Fleet(listOf(42, 50))
        assertEquals((12 + 2) + (14 + 2), fleet.requiredTotalFuel())
    }

}