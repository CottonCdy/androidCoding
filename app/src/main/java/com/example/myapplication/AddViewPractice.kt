package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat

class AddViewPractice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view_practice)

        val personList = mutableListOf<Person>()

        personList.add(Person("A", 20))
        personList.add(Person("B", 30))
        personList.add(Person("C", 35))
        personList.add(Person("D", 25))
        personList.add(Person("E", 23))
        personList.add(Person("F", 10))
        personList.add(Person("G", 34))
        personList.add(Person("H", 50))
        personList.add(Person("I", 40))
        personList.add(Person("J", 27))

        val container = findViewById<LinearLayoutCompat>(R.id.container)
        val inflater = layoutInflater

        personList.forEach { person ->

            val personItem = inflater.inflate(R.layout.person_item, null)

            val image = personItem.findViewById<ImageView>(R.id.image)
            val name = personItem.findViewById<TextView>(R.id.name)
            val age = personItem.findViewById<TextView>(R.id.age)

            image.setImageDrawable(resources.getDrawable(R.drawable.icon2, null))
            name.text = person.name
            age.text = person.age.toString()

            container.addView(personItem)

            personItem.setOnClickListener {
                val intent = Intent(this, AddViewPracticeActivity::class.java).apply {
                    this.putExtra("name", name.text)
                    this.putExtra("age", age.text)
                }
                startActivity(intent)
            }
        }
    }
}

class Person(val name: String, val age: Int)