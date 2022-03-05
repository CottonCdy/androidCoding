package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sign

class Calculater : AppCompatActivity() {

    lateinit var result: TextView

    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var buttonCa: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var buttonPlus: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var button9: Button
    lateinit var button0: Button
    lateinit var buttonEqual: Button

    var inputNumber: Int = 0
    var tempNumber: Int = 0
    var finalString: String = ""
    var onEqual: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculater)

        setButton()
        setListener()
    }

    fun setButton() {
        result = findViewById(R.id.mainText)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        buttonCa = findViewById(R.id.buttonCa)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        buttonPlus = findViewById(R.id.buttonPlus)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button0 = findViewById(R.id.button0)
        buttonEqual = findViewById(R.id.buttonEqual)
    }

    fun setListener() {
        val numberTextViewList: List<Button> = listOf(
            button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button0
        )

        val listener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (onEqual == true) {
                    result.setText("0")
                    onEqual = false
                }

                val getText: String = (p0 as Button).text.toString()


                if (result.text.equals("0") || result.text.equals("+")) {
                    result.setText("" + getText)
                } else {
                    result.setText("" + result.text + getText)
                }

                val tempText: String = result.text.toString()
                inputNumber = tempText.toInt()

                Log.d("Debuging", "" + inputNumber)
            }
        }

        numberTextViewList.forEach {
            it.setOnClickListener(listener)
        }

        // 초기화
        buttonCa.setOnClickListener {
            result.setText("0")
            inputNumber = 0
            tempNumber = 0

            Log.d("Debuging", "" + result.text + ", " + inputNumber + ", " + tempNumber)
        }

        buttonPlus.setOnClickListener {
            Log.d("result", " ")

            tempNumber += inputNumber
            result.setText("+")
            inputNumber = 0
        }

        buttonEqual.setOnClickListener {
            finalString = (tempNumber.toInt() + inputNumber.toInt()).toString()
            result.setText("" + finalString)
            Log.d("Debuging", "" + result.text + ", " + inputNumber + ", " + tempNumber)
            onEqual = true
        }
    }
}