package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ApplicationActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application1)

        findViewById<TextView>(R.id.changeActivity).setOnClickListener {
            startActivity(Intent(this, ApplicationActivity2::class.java))
        }

        findViewById<TextView>(R.id.textMethod).setOnClickListener {
            (applicationContext as MasterApplication).methodFromApplication()
            val userId = (applicationContext as MasterApplication).id
            Log.d("testt", "userId : " + userId)
        }
    }
}