package com.example.myprofile

import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity: AppCompatActivity(), Validation {

    private val etName: EditText by lazy {
        findViewById(R.id.et_name)
    }
    private val etEmail: EditText by lazy {
        findViewById(R.id.et_email)
    }
    private val etEmailChoose: EditText by lazy {
        findViewById(R.id.et_email2)
    }
    private val etPassword: EditText by lazy {
        findViewById(R.id.et_password)
    }
    private val etPasswordCheck: EditText by lazy {
        findViewById(R.id.et_password2)
    }
    private val spEmail: Spinner by lazy {
        findViewById(R.id.sp_email)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initView()
    }



    fun initView() {
        var validName = validate()
        var validEmail = validate("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+", etEmail)
        var validPassword = validate("/(?=(.*[0-9]))(?=.*[\\!@#\$%^&*()\\\\[\\]{}\\-_+=~`|:;\"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}/",etPassword)
        var validCheckPassword = validate("/(?=(.*[0-9]))(?=.*[\\!@#\$%^&*()\\\\[\\]{}\\-_+=~`|:;\"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}/",etPasswordCheck)
    }
}