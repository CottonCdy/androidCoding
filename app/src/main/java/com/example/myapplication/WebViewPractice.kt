package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged

class WebViewPractice : AppCompatActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_practice)

        val address = findViewById<EditText>(R.id.text)

        // addTextChangedListener 사용법
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

        // addTextChangedListener 사용법 -> 람다 형식
        address.doBeforeTextChanged { text, start, count, after ->  }
        address.doOnTextChanged { text, start, before, count ->  }
        address.doAfterTextChanged {  }

        (findViewById<Button>(R.id.button)).setOnClickListener {
            val getText = address.text.toString()
            webSetting(getText)
        }
    }

    fun webSetting(text: String) {
        webView = findViewById<WebView>(R.id.webView)


        webView.apply {
            webViewClient = WebViewClient() // 하이퍼링크 클릭시 새창 띄우기 방지
            webChromeClient =
                WebChromeClient() // 크롬환경에 맞는 세팅을 해줌. 특히, 알람등을 받기위해서는 꼭 선언해주어야함 (alert같은 경우)
            settings.javaScriptEnabled = true // 자바스크립트 허용
            settings.javaScriptCanOpenWindowsAutomatically = false
            // 팝업창을 띄울 경우가 있는데, 해당 속성을 추가해야 window.open() 이 제대로 작동 , 자바스크립트 새창도 띄우기 허용여부
            settings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부 (멀티뷰)
            settings.loadsImagesAutomatically = true // 웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정하는 속성
            settings.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
            settings.loadWithOverviewMode = true // 메타태그 허용 여부
            settings.setSupportZoom(true) // 화면 줌 허용여부
            settings.builtInZoomControls = false // 화면 확대 축소 허용여부
            settings.displayZoomControls = false // 줌 컨트롤 없애기.
            settings.cacheMode = WebSettings.LOAD_NO_CACHE // 웹뷰의 캐시 모드를 설정하는 속성으로써 5가지 모드
            /*
            (1) LOAD_CACHE_ELSE_NETWORK 기간이 만료돼 캐시를 사용할 수 없을 경우 네트워크를 사용합니다.
            (2) LOAD_CACHE_ONLY 네트워크를 사용하지 않고 캐시를 불러옵니다.
            (3) LOAD_DEFAULT 기본적인 모드로 캐시를 사용하고 만료된 경우 네트워크를 사용해 로드합니다.
            (4) LOAD_NORMAL 기본적인 모드로 캐시를 사용합니다.
            (5) LOAD_NO_CACHE 캐시모드를 사용하지 않고 네트워크를 통해서만 호출합니다.
             */
            settings.setAppCacheEnabled(false) // 앱 내부 캐시 사용 여부 설정 (Deprecated )
            settings.domStorageEnabled =
                true // 로컬 스토리지 사용 여부를 설정하는 속성으로 팝업창등을 '하루동안 보지 않기' 기능 사용에 필요
            settings.allowContentAccess // 웹뷰 내에서 파일 액세스 활성화 여부
            settings.userAgentString = "app" // 웹에서 해당 속성을 통해 앱에서 띄운 웹뷰로 인지 할 수 있도록 합니다.
            settings.defaultTextEncodingName = "UTF-8" // 인코딩 설정
            settings.databaseEnabled = true //Database Storage API 사용 여부 설정
        }

        webView.loadUrl(text)
    }
}