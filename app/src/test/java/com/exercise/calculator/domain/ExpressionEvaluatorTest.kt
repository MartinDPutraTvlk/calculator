package com.exercise.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ExpressionEvaluatorTest {
    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `Simple expression properly evaluated`() {
        /** Setup **/
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Operator(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Operator(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Operator(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Operator(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
            )
        )

        /** Act **/
        val actual = evaluator.evaluate()

        /** Assert **/
        val expected = 4.0

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Expression with decimals properly evaluated`() {
        /** Setup **/
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.5),
                ExpressionPart.Operator(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Operator(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Operator(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Operator(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )

        /** Act **/
        val actual = evaluator.evaluate()

        /** Assert **/
        val expected = 4.5

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Expression with parentheses properly evaluated`() {
        /** Setup **/
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Operator(Operation.ADD),
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(5.0),
                ExpressionPart.Operator(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Parentheses(ParenthesesType.Closing),
                ExpressionPart.Operator(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Operator(Operation.DIVIDE),
                ExpressionPart.Number(2.0),
            )
        )

        /** Act **/
        val actual = evaluator.evaluate()

        /** Assert **/
        val expected = 9.0

        assertThat(actual).isEqualTo(expected)
    }
}
