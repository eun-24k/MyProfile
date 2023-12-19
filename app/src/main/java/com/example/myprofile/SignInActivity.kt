package com.example.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {

    // 위젯 및 뷰 선언

    private val et_id : EditText by lazy {
        findViewById(R.id.et_id)
    }
    private val et_password : EditText by lazy {
        findViewById(R.id.et_password)
    }
    private val btn_login : Button by lazy {
        findViewById(R.id.btn_login)
    }
    private val btn_signup : Button by lazy {
        findViewById(R.id.btn_signup)
    }

    // 처음 실행되는 온크리에이트 함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // 초기 화면 함수
        initView()
    }

    /*
    실행 해야 하는 기능들:

    1. 유효성 검사
       (1.1) id는 영문과 숫자만 가능 (validEditText - validate 함수)
       (1.2) 비밀번호는 영어 대소문자 & 숫자를 무조건 가지고 있어야 하고 특수문자를 포함해야 함. (validateEditText - validate 함수)
    2. 비밀번호 EditText 입력 내용 가려주기 (input type 지정으로 해결)
    3. 로그인 버튼을 누르면 HomeActivity가 실행됩니다.
       (3.1) 아이디/비밀번호 모두 입력 되어야만 로그인 버튼이 실행됩니다. (“로그인 성공”이라는  토스트 메세지 출력)
       (3.2) 아이디/비밀번호 중 하나라도 비어 있다면 “아이디/비밀번호를 확인해주세요” 라는 토스트 메세지 출력
       (3.3) (Extra로 아이디를 넘겨줍니다.)
    4. 회원가입 버튼을 누르면 SignUpActivity가 실행됩니다. */


    private fun initView() {
        validEditText()
        buttonActivation()

    }

    fun validEditText() {
        // 아이디 영문 대소문자, 숫자, -, _ 입력 가능 (16자 이하)
        if (Pattern.compile("/^[a-z0-9_-]{3,16}\$/").matcher(et_id.toString()).find()) {
            if (Pattern.compile("/(?=(.*[0-9]))(?=.*[\\!@#\$%^&*()\\\\[\\]{}\\-_+=~`|:;\"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}/").matcher(et_password.toString()).find()) {
                buttonActivation()

            } else {
                Toast.makeText(this, "비밀번호를 다시 입력하세요.", Toast.LENGTH_SHORT).show()
            }

        } else Toast.makeText(this, "아이디를 다시 입력하세요.", Toast.LENGTH_SHORT).show()



        // 최소 영소문자/영대문자/숫자/특수문자 포함해야 하고 최소 8글자여야함.


        et_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_signup.isEnabled = et_id.text.isNotEmpty() && et_password.text.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })
        et_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                btn_signup.isEnabled = et_id.text.isNotEmpty() && et_password.text.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })
    }



    fun buttonActivation() {
        loginButton()
        signupButton()

    }

    fun loginButton() {
        btn_login.setOnClickListener {
            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
            intent.putExtra("et_id", et_id.text.toString())
            startActivity(intent)

        }
    }
    fun signupButton() {
        btn_signup.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}