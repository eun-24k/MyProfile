package com.example.myprofile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private val et_id = intent.getStringExtra("et_id")
    private val btn_finish = findViewById<Button>(R.id.btn_finish)
    private val tvId = findViewById<TextView>(R.id.tv_id)
    private val tvName = findViewById<TextView>(R.id.tv_name)
    private val tvAge = findViewById<TextView>(R.id.tv_age)
    private val tvMBTI = findViewById<TextView>(R.id.tv_mbti)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btn_finish.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }
    }



    }
}