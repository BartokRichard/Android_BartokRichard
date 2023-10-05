package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textViewBelowGrid: TextView
    private var currentExpression = ""
    private var result = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewBelowGrid = findViewById(R.id.textViewBelowGrid)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button10 = findViewById<Button>(R.id.button10)
        val button11 = findViewById<Button>(R.id.button11)
        val button12 = findViewById<Button>(R.id.button12)
        val button13 = findViewById<Button>(R.id.button13)
        val button14 = findViewById<Button>(R.id.button14)
        val button15 = findViewById<Button>(R.id.button15)
        val button16 = findViewById<Button>(R.id.button16)
        val button17 = findViewById<Button>(R.id.button17)
        val button18 = findViewById<Button>(R.id.button18)
        val button19 = findViewById<Button>(R.id.button19)

        button1.setOnClickListener {
            currentExpression = ""
            textViewBelowGrid.text = ""
        }

        button2.setOnClickListener {
            val currentText = textViewBelowGrid.text.toString()
            if (currentText.isNotEmpty()) {
                val newText = currentText.substring(0, currentText.length - 1)
                textViewBelowGrid.text = newText
                currentExpression = newText
            }
        }

        button3.setOnClickListener {
            val currentText = textViewBelowGrid.text.toString()
            if (currentText.isNotEmpty() && currentText != "Invalid Operation") {
                val number = currentText.toDouble()
                val percentage = (number / 100.0).toString()
                textViewBelowGrid.text = percentage
                currentExpression = percentage
            } else {
                // Ha a textView üres vagy érvénytelen, ne csináljunk semmit
            }
        }


        button4.setOnClickListener {
            handleOperator("/")
        }
        button5.setOnClickListener {
            addToTextView("7")
        }
        button6.setOnClickListener {
            addToTextView( "8")
        }
        button7.setOnClickListener {
            addToTextView("9")
        }
        button8.setOnClickListener {
            handleOperator("*")
        }
        button9.setOnClickListener {
            addToTextView("4")
        }
        button10.setOnClickListener {
            addToTextView("5")
        }
        button11.setOnClickListener {
            addToTextView("6")
        }
        button12.setOnClickListener {
            handleOperator("-")
        }
        button13.setOnClickListener {
            addToTextView("1")
        }
        button14.setOnClickListener {
            addToTextView("2")
        }
        button15.setOnClickListener {
            addToTextView("3")
        }
        button16.setOnClickListener {
            handleOperator("+")
        }
        button17.setOnClickListener {
            addToTextView("0")
        }
        button18.setOnClickListener {
            addToTextView(".")
        }

        button19.setOnClickListener {
            if (currentExpression.isNotEmpty()) {
                if (!currentExpression.contains("Invalid Operation")) {
                    val expressionText = textViewBelowGrid.text.toString()
                    val result = evaluateExpression(expressionText)
                    textViewBelowGrid.text = result
                    currentExpression = result
                }
            }
        }

    }
    private fun handleOperator(operator: String) {
        val currentText = textViewBelowGrid.text.toString()
        if (currentText.isNotEmpty()) {
            val lastChar = currentText.last()
            if (lastChar in "+-*/") {

                val newText = currentText.substring(0, currentText.length - 1) + operator
                textViewBelowGrid.text = newText
            } else {

                addToTextView(operator)
            }
        }
    }


    private fun addToTextView(character: String) {
        if (result.isNotEmpty()) {
            textViewBelowGrid.text = character
            currentExpression = character
            result = ""
        } else {
            val lastCharIsOperator = currentExpression.isNotEmpty() && currentExpression.last() in "+-*/"
            val parts = currentExpression.split(Regex("[+\\-*/]"))

            if (character.matches(Regex("[0-9.]")) || character in "+-*/") {
                val currentNumberHasDecimalPoint = parts.isNotEmpty() && parts.last().contains(".")

                if (character == "." && currentNumberHasDecimalPoint) {
                    return
                }

                if (character in "+-*/" && (currentExpression.isEmpty() || lastCharIsOperator)) {
                    return
                }

                currentExpression += character
                textViewBelowGrid.text = currentExpression
            }
        }
    }

    private fun evaluateExpression(expression: String): String {
        if (!isValidExpression(expression)) {
            return "Invalid Operation"
        }

        var result = 0.0
        var currentNumber = ""
        var currentOperator = '+'

        for (char in expression) {
            if (char in '0'..'9' || char == '.') {
                currentNumber += char
            } else if (char in "+-*/") {

                when (currentOperator) {
                    '+' -> result += currentNumber.toDouble()
                    '-' -> result -= currentNumber.toDouble()
                    '*' -> result *= currentNumber.toDouble()
                    '/' -> {
                        val denominator = currentNumber.toDouble()
                        if (denominator != 0.0) {
                            result /= denominator
                        } else {
                            return "Invalid Operation"
                        }
                    }
                }
                currentNumber = ""
                currentOperator = char
            }
        }


        when (currentOperator) {
            '+' -> result += currentNumber.toDouble()
            '-' -> result -= currentNumber.toDouble()
            '*' -> result *= currentNumber.toDouble()
            '/' -> {
                val denominator = currentNumber.toDouble()
                if (denominator != 0.0) {
                    result /= denominator
                } else {
                    return "Invalid Operation"
                }
            }
        }

        return result.toString()
    }

    private fun isValidExpression(expression: String): Boolean {
        val regex = Regex("([-]?[0-9]*(\\.[0-9]+)?)?[+-/*][-]?[0-9]+(\\.[0-9]+)?")
        return regex.matches(expression)
    }



}
