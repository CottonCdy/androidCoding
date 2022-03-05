package com.example.myapplication

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class Intent_One : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_one)

        // 암시적 인텐트
        // - 전화, SMS, Google Play Store, Website, GoogleMap, 사진첩 등등..
        // - 상수
        //      - 변하지 않는 수 (문자)
        //      - 상수의 변수명은 전부 대문자로 만드는 문화가 있다

        // - URI (Uniform Resource Identifier)
        //      - Id 라고 생각하면 된다 -> 고유하다
        //      - 자원을 나타내는 주소

        // - URL
        //      - 인터넷 페이지의 고유한 주소

        val implicit_intent: TextView = findViewById(R.id.implicit_intent)

        implicit_intent.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-1111-1111"))
            startActivity(intent)
        }

        // 명시적 인텐트 + ComponentName -> 액티비티 전환
        val intent_one: TextView = findViewById(R.id.intent_one)
        intent_one.setOnClickListener {
            val intent: Intent = Intent()
            val componentName: ComponentName =
                ComponentName("com.example.myapplication", "com.example.myapplication.Intent_Two")
            intent.component = componentName
            startActivity(intent)
        }

        // 명시적 인텐트 -> 액티비티 전환
        // Context
        // - 문맥
        // A 액티비티 -> B 액티비티

        /*
        val intent_two: TextView = findViewById(R.id.intent_two)

        intent_two.setOnClickListener {
            startActivity(Intent(this, Intent_Two::class.java))
        }
        */

        (findViewById<TextView>(R.id.intent_two)).apply {
            this.setOnClickListener {
                startActivity(Intent(this@Intent_One, Intent_Two::class.java))
            }
        }

        // 명시적 인텐트 + data 전달
        (findViewById<TextView>(R.id.intent_three)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                intent.putExtra("extra-data", "data")
                startActivity(intent)
            }
        }

        // 명시적 인텐트 + 결과 받기 (startActivityForResult)
        // requestCode
        // - 구분을 하기 위해서
        // - Intent_One -> Intent_Two (request 1)
        // - Intent_One -> Intent_Three (request 2)
        // - Intent_One -> Intent_Four (request 3)
        (findViewById<TextView>(R.id.intent_four)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityForResult(intent, 1)   // deprecated 되었다
            }
        }

        // 명시적 인텐트 + 결과 받기 (ActivityResult API)
        // - requestCode 가 존재하지 않는다 -> requestCode 없이도 요청자를 구분할 수 있다
        val startActivityLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {
                // onActivityResult에 해당하는 부분
                when (it.resultCode) {
                    RESULT_OK -> {
                        Log.d("dataLog", it.data?.extras?.getString("result")!!)
                    }
                }

                // onActivityResult
                // - 모든 intent 가 한 곳에서 처리된다 -> 구분이 필요하다(request code)

                // ActivityResult API
                // - 각각의 Intent 가 처리되는 곳이 별도로 있다 -> 구분이 필요없다
            }

        (findViewById<TextView>(R.id.intent_five)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityLauncher.launch(intent)
            }
        }

        // 명시적 인텐트 + 이미지 URI 전달
        (findViewById<TextView>(R.id.intent_six)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java).apply {
                    val imageUri =
                        Uri.parse("android.resource://" + packageName + "/drawable/" + "icon2")
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_STREAM, imageUri)
                    this.setType("image/*")
                }

                startActivity(intent)
            }
        }

        // 인텐트를 이용해서 데이터 전달이 가능하다
        // - 인텐트를 이용해서 키밸류 데이터를 전달한다
        // - 인텐트를 이용해서 이미지를 전달한다
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // resultCode (status code)
        // - 최종결과
        // - ex) 성공, 실패
        when (requestCode) {
            1 -> {
                when (resultCode) {
                    RESULT_OK -> {
                        val data = data?.extras?.getString("result")
                        Log.d("dataLog", data!!)
                    }
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}