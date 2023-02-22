package com.exercise.calculator.domain

sealed interface CalculationAction {
    data class Number(val number: Int): CalculationAction
    data class Operator(val operation: Operation): CalculationAction
    object Clear: CalculationAction
    object Delete: CalculationAction
    object Parentheses: CalculationAction
    object Calculate: CalculationAction
    object Decimal: CalculationAction
}
