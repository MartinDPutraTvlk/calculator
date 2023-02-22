package com.exercise.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionWriterTest {
    private lateinit var writer: ExpressionWriter

    @Before
    fun setup() {
        writer = ExpressionWriter()
    }

    @Test
    fun `Initial parentheses parsed`() {
        /** Act **/
        writer.processAction(CalculationAction.Parentheses)
        writer.processAction(CalculationAction.Number(5))
        writer.processAction(CalculationAction.Operator(Operation.ADD))
        writer.processAction(CalculationAction.Number(4))
        writer.processAction(CalculationAction.Parentheses)

        /** Assert **/
        assertThat(writer.expression).isEqualTo("(5+4)")
    }

    @Test
    fun `Closing parentheses at the start not parsed`() {
        /** Act **/
        writer.processAction(CalculationAction.Parentheses)
        writer.processAction(CalculationAction.Parentheses)

        /** Assert **/
        assertThat(writer.expression).isEqualTo("((")
    }

    @Test
    fun `Parentheses around a number are parsed`() {
        /** Act **/
        writer.processAction(CalculationAction.Parentheses)
        writer.processAction(CalculationAction.Number(6))
        writer.processAction(CalculationAction.Parentheses)

        /** Assert **/
        assertThat(writer.expression).isEqualTo("(6)")
    }
}
