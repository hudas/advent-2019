package org.local

import java.lang.IllegalArgumentException

data class Pin(val x: Int, val y: Int)

class CircuitBoard(val wirePaths: Map<String, List<String>>) {

    val wires: Set<String>
        get() = wirePaths.keys

    fun wireConnections(wire: String): Set<Pin> {
        val selectedWire = wirePaths[wire] ?: listOf()

        var currentPinPosition = Pin(0, 0)

        var wirePinPositions = mutableSetOf<Pin>()
        for (index in selectedWire.indices) {
            val pathIncrement = selectedWire[index]

            val nextPins = buildPinsInBetween(currentPinPosition, pathIncrement)
            wirePinPositions.addAll(nextPins)
            currentPinPosition = nextPins.last()
        }

        return wirePinPositions
    }

    fun wireNonUniqConnections(wire: String): List<Pin> {
        val selectedWire = wirePaths[wire] ?: listOf()

        var currentPinPosition = Pin(0, 0)

        var wirePinPositions = mutableListOf<Pin>()
        for (index in selectedWire.indices) {
            val pathIncrement = selectedWire[index]

            val nextPins = buildPinsInBetween(currentPinPosition, pathIncrement)
            wirePinPositions.addAll(nextPins)
            currentPinPosition = nextPins.last()
        }

        return wirePinPositions
    }

    fun buildPinsInBetween(currentPin: Pin, increment: String): Set<Pin> {
        val sectionLength = increment.substring(1).toInt();
        return when(increment[0]) {
            'U' -> (1..sectionLength).map { Pin(currentPin.x, currentPin.y + it) }.toSet()
            'R' -> (1..sectionLength).map { Pin(currentPin.x + it, currentPin.y) }.toSet()
            'D' -> (1..sectionLength).map { Pin(currentPin.x, currentPin.y - it) }.toSet()
            'L' -> (1..sectionLength).map { Pin(currentPin.x - it, currentPin.y) }.toSet()
            else -> throw IllegalArgumentException()
        }
    }

}