package com.exercise.calculator.domain

sealed interface CalculatorAction {
    data class Number(val number: Int): CalculatorAction
    data class Operator(val operation: Operation): CalculatorAction
    object Clear: CalculatorAction
    object Delete: CalculatorAction
    object Parentheses: CalculatorAction
    object Calculate: CalculatorAction
    object Decimal: CalculatorAction
}
