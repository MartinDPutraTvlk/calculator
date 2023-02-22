package com.exercise.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {
        /** Setup **/
        parser = ExpressionParser("3+5-3x4/3")

        /** Act **/
        val actual = parser.parse()

        /** Assert **/
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Operator(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Operator(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Operator(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Operator(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
        )

        assertThat(actual).isEqualTo(expected)
    }
    
    @Test
    fun `Expression with parentheses is properly parsed`() {
        /** Setup **/
        parser = ExpressionParser("4-(4x5)")

        /** Act **/
        val actual = parser.parse()
        
        /** Assert **/
        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.Operator(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ParenthesesType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.Operator(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parentheses(ParenthesesType.Closing),
        )
        assertThat(actual).isEqualTo(expected)
    }
}
