package com.fooddelivery

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.fooddelivery.R

class SplashActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var btnGetStarted: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        progressBar = findViewById(R.id.loader)
        btnGetStarted = findViewById(R.id.btnGetStarted)

        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, 5000)

        btnGetStarted.setOnClickListener {
            progressBar.visibility = ProgressBar.VISIBLE
            Handler().postDelayed({
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }, 5000)
        }
    }

}