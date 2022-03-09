package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AddViewPracticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view_practice2)

        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)

        val intent = intent
        val getName = intent.getStringExtra("name")
        val getAge = intent.getStringExtra("age")

        name.text = getName
        age.text = getAge
    }
}