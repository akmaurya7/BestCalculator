package com.example.bestcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var tVInputNO: TextView
    private lateinit var tVResult: TextView
    private var isResultDisplayed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                tVInputNO = findViewById(R.id.tVInputNO)
                tVResult = findViewById(R.id.tVResult)

                val buttonIds = arrayOf(
                    R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                    R.id.btn8, R.id.btn9, R.id.btnDecimal, R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDivide
                )

                for (buttonId in buttonIds) {
                    val button = findViewById<Button>(buttonId)
                    button.setOnClickListener {
                        appendToInput(button.text.toString())

                    }
                }

                val btnResult = findViewById<Button>(R.id.btnResult)
                btnResult.setOnClickListener {
                    evaluateExpression()
                    isResultDisplayed = true

                }
                val btnAC = findViewById<Button>(R.id.btnAC)
                btnAC.setOnClickListener {
                    clearInput()
                }
                val btnDel = findViewById<Button>(R.id.btnDel)
                btnDel.setOnClickListener{
                    appendToInputt(btnDel.text.toString())
                }
            }

            private fun appendToInput(text: String) {
                val currentText = tVInputNO.text.toString()
                tVInputNO.text = "$currentText$text"
            }

            private fun evaluateExpression() {
                val expression = tVInputNO.text.toString()

                try {
                    val mathExpression: Expression = ExpressionBuilder(expression).build()
                    val result = mathExpression.evaluate()
                    tVResult.text = result.toString()
                } catch (e: Exception) {
                    tVResult.text = "Error"
                }
            }
        private fun clearInput() {
            tVInputNO.text = ""
            tVResult.text = ""
            isResultDisplayed = false
    }
    private fun appendToInputt(text: String) {
        val currentText = tVInputNO.text.toString()

        if (isResultDisplayed) {
            // Reset the input and display the new text
            tVInputNO.text = text
            isResultDisplayed = false
        } else {
            // Check if the "Delete" button was clicked and there is something to delete
            if (text == "Del" && currentText.isNotEmpty()) {
                // Remove the last character from the input text
                tVInputNO.text = currentText.substring(0, currentText.length - 1)
            } else if (text != "Del") {
                // Append the new text to the existing input
                tVInputNO.text = "$currentText$text"
            }
        }
    }


}