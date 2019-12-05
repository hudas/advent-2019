package org.local

class Fleet(val modules: List<Int>) {

    // Day 1, part 1 solution
    fun requiredFuelForModules() = modules
        .map { calculateFuelToCarryModule(it) }
        .sum()

    // Day 1, part 2 solution
    fun requiredTotalFuel() = modules
        .map { calculateFuelToCarryModule(it) }
        .map { adjustForFuelMass(it) }
        .sum()

    private fun adjustForFuelMass(mass: Int): Int  {
        if (mass <= 6) {
            return mass;
        }

        return adjustForFuelMass(calculateFuelToCarryModule(mass)) + mass
    }

    private fun calculateFuelToCarryModule(mass: Int) = mass / 3 - 2
}