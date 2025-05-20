package com.example.mycalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstNumber = findViewById<EditText>(R.id.firstNumber)
        val secondNumber = findViewById<EditText>(R.id.secondNumber)
        val resultText = findViewById<TextView>(R.id.textResult)

        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSub = findViewById<Button>(R.id.buttonSub)
        val buttonMul = findViewById<Button>(R.id.buttonMul)
        val buttonDiv = findViewById<Button>(R.id.buttonDiv)
        val buttonFullCalculator = findViewById<Button>(R.id.buttonFullCalculator)

        val calculate: (String) -> Unit = calculate@{ operation ->
            val num1 = firstNumber.text.toString().toDoubleOrNull()
            val num2 = secondNumber.text.toString().toDoubleOrNull()

            if (num1 == null || num2 == null) {
                resultText.text = "Enter valid numbers!"
                return@calculate
            }

            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "×" -> num1 * num2
                "÷" -> if (num2 != 0.0) num1 / num2 else "Error"
                else -> "Error"
            }

            resultText.text = "Result: $result"
        }

        buttonAdd.setOnClickListener { calculate("+") }
        buttonSub.setOnClickListener { calculate("-") }
        buttonMul.setOnClickListener { calculate("×") }
        buttonDiv.setOnClickListener { calculate("÷") }

        buttonFullCalculator.setOnClickListener {
            val intent = Intent(this, FullCalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}
