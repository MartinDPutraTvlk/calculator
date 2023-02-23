package com.exercise.calculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.exercise.calculator.domain.CalculatorAction
import com.exercise.calculator.domain.ExpressionWriter


class CalculatorViewModel constructor(
    private val writer: ExpressionWriter = ExpressionWriter()
) : ViewModel() {
    var expression by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        writer.processAction(action)
        this.expression = writer.expression
    }
}
