package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        val imageView = findViewById<ImageView>(R.id.image)
        val url =
            "https://c66af6c7-a-62cb3a1a-s-sites.googlegroups.com/site/mamekhs1/baegyeonghwamyeon/baegyeong2/661717535_0100400f_6.jpg?attachauth=ANoY7cqeK4TtnYt84NQp2hd_PKQhMzdB0ofMEh_LDqcKlIbZ_iVdiVvq_PZBPwxHehXQ0O2lfN1SZS-IiXrgSfzDlAe7OPuAJbKu49NOCinWtUHedkkRsO4mlRkoaMVhsSkd3gmTcIqGBgvR3ybqhB8Xn9s7HY6blbfUUtZ7uUcdsKEA339tS5IF8h9_1OErxdNRoPjnv1VAr7Mng1cEQZGbfshjO9e38-k_RyMtRD1cOAgjWAwZQr4IDnjPM3LETB1LTxf8xoZJ9VP3Vu5E19V1rtxHjrA5EA%3D%3D&attredirects=0"

        Glide
            .with(this)
            .load(url)
            .centerCrop()
            .into(imageView)
    }
}