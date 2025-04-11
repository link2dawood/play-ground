package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var displayText: TextView
    private var current = ""
    private var operator = ""
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayText = findViewById(R.id.display)
        val buttonGrid = findViewById<GridLayout>(R.id.buttonGrid)

        for (i in 0 until buttonGrid.childCount) {
            val btn = buttonGrid.getChildAt(i) as Button
            btn.setOnClickListener { onButtonClick(btn.text.toString()) }
        }
    }

    private fun onButtonClick(value: String) {
        when (value) {
            "C" -> {
                current = ""
                operator = ""
                result = 0.0
                displayText.text = "0"
            }
            "=" -> {
                val second = current.toDoubleOrNull() ?: return
                result = when (operator) {
                    "+" -> result + second
                    "-" -> result - second
                    "×" -> result * second
                    "÷" -> if (second != 0.0) result / second else Double.NaN
                    else -> second
                }
                displayText.text = result.toString()
                current = ""
            }
            "+", "-", "×", "÷" -> {
                result = current.toDoubleOrNull() ?: result
                operator = value
                current = ""
            }
            else -> {
                current += value
                displayText.text = current
            }
        }
    }
}
