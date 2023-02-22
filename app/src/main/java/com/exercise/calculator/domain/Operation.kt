package com.exercise.calculator.domain

enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%'),
}

val operationSymbols = Operation
    .values()
    .map { it.symbol }
    .joinToString("")

fun operationFromSymbol(c: Char): Operation {
    return Operation.values().find { it.symbol == c } ?:
        throw IllegalArgumentException("Invalid symbol")
}
