package com.exercise.calculator.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.exercise.calculator.MainActivity
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun enterExpression_correctResultDisplayed() {
        performClickOnButtonWithText("1")
        performClickOnButtonWithText("+")
        performClickOnButtonWithText("2")
        performClickOnButtonWithText("x")
        performClickOnButtonWithText("3")
        performClickOnButtonWithText("-")
        performClickOnButtonWithText("5")
        performClickOnButtonWithText("=")

        composeRule.onNodeWithText("2.0").assertIsDisplayed()
    }

    private fun performClickOnButtonWithText(text: String) {
        composeRule.onNodeWithText(text).performClick()
    }
}
