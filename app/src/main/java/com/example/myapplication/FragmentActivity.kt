package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class FragmentActivity : AppCompatActivity() {
    var firstFragmentNullCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragmentManager = supportFragmentManager
        val fragmentFirst = FragmentFirst()


        // Transaction
        // - 작업의 단위 -> 시작과 끝이 있다

        // Commit
        // 1. commit
        // 2. commitAllowingStateLoss
        // 3. commitNow
        // 4. commitNowAllowingStateLoss

        // commit - commitAllowingStateLoss(AllowingStateLoss)
        // - 상태손실을 허락한다
        // - onStop, lifecycle or OS에 의해서 앱이 상태를 저장해야 할 수 있다
        // - 상태저장 : onSaveInstanceState
        // - commit : 저장을 한 경우 실행할 수 없다 (예외가 발생한다) (IllegalStateException)
        // - commitAllowingStateLoss : 저장을 한 경우 예외가 발생하지 않고 그냥 손실

        // commit - commitNow(Now)
        // - commit -> 작업을 예약한다 (메인 쓰레드에 예약이 된다)
        // - commitNow -> 바로 실행해!

        (findViewById<Button>(R.id.add)).setOnClickListener {
            val transaction = fragmentManager.beginTransaction()    // 시작

            // 프래그먼트에 데이터를 전달하는 방법 Bundle 이용
            val bundle = Bundle()
            bundle.putString("key", "hello data")
            fragmentFirst.arguments = bundle

            transaction.replace(R.id.root, fragmentFirst, "fragment_first_tag")
            transaction.commit()    // 끝
            firstFragmentNullCheck = true
        }

        (findViewById<Button>(R.id.remove)).setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(fragmentFirst)
            transaction.commit()
            firstFragmentNullCheck = false
        }

        (findViewById<TextView>(R.id.access_fragment)).setOnClickListener {
            // XML에 있는 Fragment를 찾는 방법
            /*
            val fragmentFirst =
                supportFragmentManager.findFragmentById(R.id.fragment_first) as FragmentFirst

            fragmentFirst.printTestLog()
            */

            // XML에 없는 Fragment를 찾는 방법 -> Fragment가 생성되지 않았을 경우 예외 발생
            if (firstFragmentNullCheck) {
                val fragmentFirst =
                    supportFragmentManager.findFragmentByTag("fragment_first_tag") as FragmentFirst

                fragmentFirst.printTestLog()
            } else {
                Log.d("testt", "FragmentFirst Not Found")
            }
        }
    }

    fun printTestLog() {
        Log.d("testt", "print test log")
        // git test 수정
    }
}