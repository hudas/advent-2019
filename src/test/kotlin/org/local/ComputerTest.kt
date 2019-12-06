package org.local

import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertEquals

class ComputerTest {

    @Test
    fun shouldTerminateProgramAfterTerminationInstruction() {
        val computer = Computer(listOf(99))
        assertEquals(listOf(99), computer.executeInstructions())
    }

    @Test
    fun shouldExecuteAdditionInstruction() {
        val computer = Computer(listOf(1, 0, 0, 0, 99))
        assertEquals(listOf(2, 0, 0, 0, 99), computer.executeInstructions())
    }

    @Test
    fun shouldExecuteMultiplicationInstruction() {
        val computer = Computer(listOf(2, 1, 1, 0, 99))
        assertEquals(listOf(1, 1, 1, 0, 99), computer.executeInstructions())
    }

    @Test
    fun shouldStoreResultInPositionProvidedForInstruction() {
        val firstComputer = Computer(listOf(1, 1, 2, 1, 99))
        assertEquals(listOf(1, 3, 2, 1, 99), firstComputer.executeInstructions())

        val secondComputer = Computer(listOf(2, 3, 0, 3, 99))
        assertEquals(listOf(2, 3, 0, 6, 99), secondComputer.executeInstructions())
    }

    @Test
    fun shouldStoreResultInPositionProvidedForInstructionIfPositionIsOutsideInstructionSet() {
        val firstComputer = Computer(listOf(2, 4, 4, 5, 99, 0))
        assertEquals(listOf(2, 4, 4, 5, 99, 9801), firstComputer.executeInstructions())
    }

    @Test
    fun shouldExecuteMultipleInstructions() {
        val firstComputer = Computer(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99))
        assertEquals(listOf(30, 1, 1, 4, 2, 5, 6, 0, 99), firstComputer.executeInstructions())
    }

    @Ignore
    @Test
    fun findInput() {
        val firstComputer = Computer(
            listOf(
                1,
                0,
                0,
                3,
                1,
                1,
                2,
                3,
                1,
                3,
                4,
                3,
                1,
                5,
                0,
                3,
                2,
                13,
                1,
                19,
                1,
                5,
                19,
                23,
                2,
                10,
                23,
                27,
                1,
                27,
                5,
                31,
                2,
                9,
                31,
                35,
                1,
                35,
                5,
                39,
                2,
                6,
                39,
                43,
                1,
                43,
                5,
                47,
                2,
                47,
                10,
                51,
                2,
                51,
                6,
                55,
                1,
                5,
                55,
                59,
                2,
                10,
                59,
                63,
                1,
                63,
                6,
                67,
                2,
                67,
                6,
                71,
                1,
                71,
                5,
                75,
                1,
                13,
                75,
                79,
                1,
                6,
                79,
                83,
                2,
                83,
                13,
                87,
                1,
                87,
                6,
                91,
                1,
                10,
                91,
                95,
                1,
                95,
                9,
                99,
                2,
                99,
                13,
                103,
                1,
                103,
                6,
                107,
                2,
                107,
                6,
                111,
                1,
                111,
                2,
                115,
                1,
                115,
                13,
                0,
                99,
                2,
                0,
                14,
                0
            )
        )
        val input = Debugger(firstComputer).findInputMatchingResult(19690720)
        assertEquals(ProgramInput(0, 0), input)
    }
}