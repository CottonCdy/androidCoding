package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class Intent_Two : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_two)

        // Intent_Two 액티비티를 호출한 Activity 의 intent 이다
        val intent = intent

        // Intent 에 데이터가 있을 수도 있고 없을 수도 있어서 nullable 타입이다
        val data = intent.extras?.getString("extra-data")

        if (data != null) {
            Log.d("dataLog", data)
        }

        (findViewById<TextView>(R.id.finish)).apply {
            this.setOnClickListener {
                val intent = Intent()
                intent.putExtra("result", "success")
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        val imageView: ImageView = findViewById(R.id.imageView)
        val uri = Uri.parse(intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString())

        imageView.setImageURI(uri)

    }
}