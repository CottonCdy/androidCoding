package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        val backgroundTask = BackgroundAsyncTast(
            findViewById(R.id.progrssBar),
            findViewById(R.id.progrssBarText)
        )

        findViewById<TextView>(R.id.start).setOnClickListener {
            backgroundTask.execute()
        }

        findViewById<TextView>(R.id.stop).setOnClickListener {
            backgroundTask.cancel(true)
        }

        findViewById<TextView>(R.id.shot).setOnClickListener {
            Log.d("testt", "shot!")
        }
    }
}

class BackgroundAsyncTast(val progressbar: ProgressBar, val progressText: TextView) :
    AsyncTask<Int, Int, Int>() {
    // deprecated -> 더 이상 사용을 권장하지 않는다

    // Params, Progress, Result
    // Params -> doInBackground에서 사용할 타입
    // Progress -> onProgressUpdate에서 사용할 타입
    // Result -> onPostExecute에서 사용할 타입

    // 코루틴 -> 멀티 태스킹, 동기 / 비동기 처리

    var percent: Int = 0

    override fun doInBackground(vararg p0: Int?): Int {
        while (isCancelled() == false) {
            percent++
            if (percent > 100)
                break
            else
                publishProgress(percent)
            Thread.sleep(100)
        }

        return percent
    }

    override fun onPreExecute() {
        percent = 0
        progressbar.setProgress(percent)
    }

    override fun onPostExecute(result: Int?) {
        progressText.text = "작업이 완료 되었습니다"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        progressbar.setProgress(values[0] ?: 0)
        progressText.text = "퍼센트 : " + values[0]
    }

    override fun onCancelled() {
        progressText.text = "작업이 취소 되었습니다"
    }
}