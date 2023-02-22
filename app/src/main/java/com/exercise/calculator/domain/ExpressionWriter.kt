package com.exercise.calculator.domain

class ExpressionWriter {

    var expression = ""

    fun processAction(action: CalculationAction) {
        when (action) {
            CalculationAction.Calculate -> {
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExpressionEvaluator(parser.parse())
                expression = evaluator.evaluate().toString()
            }
            CalculationAction.Clear -> {
                expression = ""
            }
            CalculationAction.Decimal -> {
                if (canEnterDecimal()) {
                    expression += "."
                }
            }
            CalculationAction.Delete -> {
                expression = expression.dropLast(1)
            }
            is CalculationAction.Number -> {
                expression += action.number
            }
            is CalculationAction.Operator -> {
                if (canEnterOperation(action.operation)) {
                    expression += action.operation.symbol
                }
            }
            CalculationAction.Parentheses -> {
                processParentheses()
            }
        }
    }

    private fun prepareForCalculation(): String {
        val newExpression = expression.takeLastWhile {
            it in "$operationSymbols(."
        }
        if(newExpression.isEmpty()) {
            return "0"
        }
        return newExpression
    }

    private fun processParentheses() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }
        expression += when {
            expression.isEmpty() || expression.last() in "$operationSymbols(" -> "("
            expression.last() in "0123456789)" && openingCount == closingCount -> return
            else -> ")"
        }
    }

    private fun canEnterDecimal(): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationSymbols.()") {
            return false
        }
        return !expression.takeLastWhile {
            it in "123456789."
        }.contains(".")
    }

    private fun canEnterOperation(operation: Operation): Boolean {
        if (operation in listOf(Operation.ADD, Operation.SUBTRACT)) {
            return expression.isEmpty() || expression.last() in "$operationSymbols()123456789"
        }
        return expression.isNotEmpty() || expression.last() in "0123456789)"
    }
}
