package com.example.myprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {

    private val userId = intent.getStringExtra("et_id")
    private val userName = "김은이"
    private val userAge = 21
    private val userMBTI = "ISTP"
    private val ivProfile = findViewById<ImageView>(R.id.iv_me)
    private val btn_finish = findViewById<Button>(R.id.btn_finish)
    private val tvId = findViewById<TextView>(R.id.tv_id)
    private val tvName = findViewById<TextView>(R.id.tv_name)
    private val tvAge = findViewById<TextView>(R.id.tv_age)
    private val tvMBTI = findViewById<TextView>(R.id.tv_mbti)


    val images = arrayOf(R.drawable.profile_picture, R.drawable.volunteer, R.drawable.me, R.drawable.badminton, R.drawable.piano)
    val random = Random.nextInt(images.size)


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        ivProfile.setImageResource(images[random])
        tvId.setText("아이디 : ${userId}")
        tvName.setText("이름 : ${userName}")
        tvAge.setText("나이 : ${userAge}")
        tvMBTI.setText("MBTI : ${userMBTI}")


        btn_finish.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}