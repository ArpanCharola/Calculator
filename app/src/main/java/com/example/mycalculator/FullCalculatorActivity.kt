package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class FullCalculatorActivity : AppCompatActivity() {

    private lateinit var etExpression: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_calculator)

        etExpression = findViewById(R.id.etExpression)
        tvResult = findViewById(R.id.tvResult)

        val buttons = listOf(
            Pair(R.id.btn0, "0"), Pair(R.id.btn1, "1"), Pair(R.id.btn2, "2"),
            Pair(R.id.btn3, "3"), Pair(R.id.btn4, "4"), Pair(R.id.btn5, "5"),
            Pair(R.id.btn6, "6"), Pair(R.id.btn7, "7"), Pair(R.id.btn8, "8"),
            Pair(R.id.btn9, "9"), Pair(R.id.btnPlus, "+"), Pair(R.id.btnMinus, "-"),
            Pair(R.id.btnMultiply, "*"), Pair(R.id.btnDivide, "/"),
            Pair(R.id.btnDot, "."), Pair(R.id.btnOpenBracket, "("),
            Pair(R.id.btnCloseBracket, ")")
        )

        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                etExpression.append(value)
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            etExpression.setText("")
            tvResult.text = "Result:"
        }

        findViewById<Button>(R.id.btnBackspace).setOnClickListener {
            val text = etExpression.text.toString()
            if (text.isNotEmpty()) {
                etExpression.setText(text.dropLast(1))
                etExpression.setSelection(etExpression.text.length)
            }
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            val expression = etExpression.text.toString()
            try {
                val result = ExpressionBuilder(expression).build().evaluate()
                tvResult.text = "Result: $result"
            } catch (e: Exception) {
                tvResult.text = "Invalid Expression"
            }
        }
    }
}
