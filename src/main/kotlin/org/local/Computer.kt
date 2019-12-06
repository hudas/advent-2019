package org.local

import java.lang.IllegalArgumentException

const val INSTRUCTION_LENGTH = 4;

data class ProgramInput(val noun: Int, val verb: Int)

class Computer(val instructions: List<Int>) {

    fun executeInstructions() = when(instructions) {
        listOf(99) -> instructions
        else -> executeInstructionsWithInput(ProgramInput(instructions[1], instructions[2]))
    }

    fun executeInstructionsWithInput(input: ProgramInput): List<Int> {
        val instructionsWithInput = applyInput(input)
        var instructionPointer = 0;

        var resultingIntruction = instructionsWithInput
        while (true) {
            if (isHaltInstruction(resultingIntruction, instructionPointer)) {
                break
            }

            resultingIntruction = executeInstruction(resultingIntruction, instructionPointer)
            instructionPointer += INSTRUCTION_LENGTH
        }

        return resultingIntruction
    }

    private fun executeInstruction(
        resultingIntruction: List<Int>,
        instructionPointer: Int
    ): List<Int> {
        var resultingIntruction1 = resultingIntruction
        val instructionResult = applyInstruction(
            resultingIntruction1[instructionPointer],
            resultingIntruction1[resultingIntruction1[instructionPointer + 1]],
            resultingIntruction1[resultingIntruction1[instructionPointer + 2]]
        )

        resultingIntruction1 = updateValueAtMemmoryAddress(
            resultingIntruction1,
            instructionResult,
            resultingIntruction1[instructionPointer + 3]
        )
        return resultingIntruction1
    }

    private fun isHaltInstruction(
        resultingIntruction: List<Int>,
        instructionPointer: Int
    ) = resultingIntruction[instructionPointer] == 99

    private fun applyInput(input: ProgramInput): List<Int> {
        var instructionsWithNoun = updateValueAtMemmoryAddress(instructions, input.noun, 1)
        return updateValueAtMemmoryAddress(instructionsWithNoun, input.verb, 2)
    }

    private fun applyInstruction(instruction: Int, firstParam: Int, secondParam: Int) = when(instruction) {
        1 -> firstParam + secondParam
        2 -> firstParam * secondParam
        else -> throw IllegalArgumentException()
    }

    private fun updateValueAtMemmoryAddress(instructions: List<Int>, result: Int, resultIndex: Int): List<Int> =
        instructions.mapIndexed { index, instruction -> if (index == resultIndex) result else instruction }
}