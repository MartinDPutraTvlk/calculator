package com.exercise.calculator.domain

/**
 * Uses the following grammar
 * expression :	term | term + term | term − term
 * term :		factor | factor * factor | factor / factor | factor % factor
 * factor : 	number | ( expression ) | + factor | − factor
 */
class ExpressionEvaluator(
    private val expression: List<ExpressionPart>
) {
    fun evaluate(): Double {
        return evaluateExpression(expression).value
    }

    /**
     * A factor is either a number or an expression in parentheses
     * e.g. 5.0, -7.5, -(3+4*5)
     * But NOT something like 3 * 5, 4 + 5
     */
    private fun evaluateFactor(expression: List<ExpressionPart>): ExpressionResult {
        return when(val part = expression.firstOrNull()) {
            ExpressionPart.Operator(Operation.ADD) -> {
                evaluateFactor(expression.drop(1))
            }
            ExpressionPart.Operator(Operation.SUBTRACT) -> {
                evaluateFactor(expression.drop(1)).run {
                    ExpressionResult(remainingExpression, -value)
                }
            }
            ExpressionPart.Parentheses(ParenthesesType.Opening) -> {
                evaluateExpression(expression.drop(1)).run {
                    ExpressionResult(remainingExpression.drop(1), value)
                }
            }
            ExpressionPart.Operator(Operation.PERCENT) -> evaluateTerm(expression.drop(1))
            is ExpressionPart.Number -> ExpressionResult(
                remainingExpression = expression.drop(1),
                value = part.number
            )
            else -> throw RuntimeException("Invalid part")
        }
    }

    private fun evaluateTerm(expression: List<ExpressionPart>): ExpressionResult {
        val result = evaluateFactor(expression)
        var remaining = result.remainingExpression
        var sum = result.value
        while(true) {
            when(remaining.firstOrNull()) {
                ExpressionPart.Operator(Operation.MULTIPLY) -> {
                    val factor = evaluateFactor(remaining.drop(1))
                    sum *= factor.value
                    remaining = factor.remainingExpression
                }
                ExpressionPart.Operator(Operation.DIVIDE) -> {
                    val factor = evaluateFactor(remaining.drop(1))
                    sum /= factor.value
                    remaining = factor.remainingExpression
                }
                ExpressionPart.Operator(Operation.PERCENT) -> {
                    val factor = evaluateFactor(remaining.drop(1))
                    sum *= (factor.value / 100)
                    remaining = factor.remainingExpression
                }
                else -> return ExpressionResult(remaining, sum)
            }
        }
    }

    private fun evaluateExpression(expression: List<ExpressionPart>): ExpressionResult {
        val result = evaluateTerm(expression)
        var remaining = result.remainingExpression
        var sum = result.value
        while(true) {
            when(remaining.firstOrNull()) {
                ExpressionPart.Operator(Operation.ADD) -> {
                    val term = evaluateTerm(remaining.drop(1))
                    sum += term.value
                    remaining = term.remainingExpression
                }
                ExpressionPart.Operator(Operation.SUBTRACT) -> {
                    val term = evaluateTerm(remaining.drop(1))
                    sum -= term.value
                    remaining = term.remainingExpression
                }
                else -> return ExpressionResult(remaining, sum)
            }
        }
    }

    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double,
    )
}
