package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.*
import android.widget.Button
import android.widget.EditText
import java.lang.Exception

class WebViewPractice2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_practice2)

        val address = findViewById<EditText>(R.id.text)

        address.addTextChangedListener(object : TextWatcher {
            // 글자가 변경되기 전
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("testt", "beforeTextChanged : " + s)
            }

            // 글자가 변경되는 중
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("testt", "onTextChanged : " + s)
            }

            // 글자가 변경된 후
            override fun afterTextChanged(s: Editable?) {
                Log.d("testt", "afterTextChanged : " + s)

            }
        })

        val webView = findViewById<WebView>(R.id.webView)

        webView.apply {
            webView.webViewClient = WebViewClient()
        }

        try {
            webView.loadUrl(
                intent.data!!.toString()
            )
        } catch (exception: Exception) {

        }

        (findViewById<Button>(R.id.button)).setOnClickListener {
            val getText = address.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getText))
            startActivity(intent)
        }
    }
}