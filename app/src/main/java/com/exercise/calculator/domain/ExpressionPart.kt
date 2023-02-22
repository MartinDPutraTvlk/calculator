package com.exercise.calculator.domain

sealed interface ExpressionPart {
    data class Number(val number: Double): ExpressionPart
    data class Operator(val type: Operation): ExpressionPart
    data class Parentheses(val type: ParenthesesType): ExpressionPart
}

sealed interface ParenthesesType {
    object Opening: ParenthesesType
    object Closing: ParenthesesType
}
