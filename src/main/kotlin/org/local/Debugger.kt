package org.local

class Debugger(val computer: Computer) {
    private val inputsToTest = (0..99).flatMap { noun -> (0..99).map { verb -> ProgramInput(noun, verb) } }

    fun findInputMatchingResult(result: Int) = inputsToTest
        .filter { computer.executeInstructionsWithInput(it)[0] == result }
        .first()
}