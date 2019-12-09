package org.local

import kotlin.math.absoluteValue

class MultiMeter {
    fun findShortedPin(circuit: CircuitBoard): Pin {
        val allPins = mutableMapOf<Pin, Int>()

        circuit.wires.forEach {
            circuit.wireConnections(it).forEach { pin ->
                val connectorCount = allPins.getOrDefault(pin, 0)
                allPins.put(pin, connectorCount + 1)
            }
        }

        val shortedPins = allPins.filter { it.value > 1 }
        val shortedPairs = shortedPins.map { Pair(it.key, it.key.x.absoluteValue + it.key.y.absoluteValue) }
        val sortedShortedPairs = shortedPairs.sortedBy { it.second }

        return sortedShortedPairs
            .map { it.first }
            .first()
    }

    fun findShortedPinWithMinimalDelay(circuit: CircuitBoard): Pair<Pin, Int> {
        val allPins = mutableMapOf<Pin, Int>()

        circuit.wires.forEach {
            circuit.wireConnections(it).forEach { pin ->
                val connectorCount = allPins.getOrDefault(pin, 0)
                allPins.put(pin, connectorCount + 1)
            }
        }

        val shortedPins = allPins.filter { it.value > 1 }

        return shortedPins
            .map { pinEntry ->
                val pin = pinEntry.key
                val wireLength = circuit.wires.map { circuit.wireNonUniqConnections(it).indexOf(pin) + 1 }.sum()

                Pair(pin, wireLength)
            }
            .minBy { it.second }!!
    }

    fun distanceToPin(pin: Pin) = pin.x.absoluteValue + pin.y.absoluteValue
}