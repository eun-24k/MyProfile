package com.example.myprofile

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener

class SignUpActivity: AppCompatActivity(), EditTextValidation {

    private val etName: EditText by lazy {
        findViewById(R.id.et_name)
    }
    private val etEmail: EditText by lazy {
        findViewById(R.id.et_email)
    }
    private val etEmailProvider: EditText by lazy {
        findViewById(R.id.et_email2)
    }
    private val etPassword: EditText by lazy {
        findViewById(R.id.et_password)
    }
    private val etPasswordCheck: EditText by lazy {
        findViewById(R.id.et_password2)
    }
    private val typeName: TextView by lazy {
        findViewById(R.id.type_name)
    }
    private val typeEmail: TextView by lazy {
        findViewById(R.id.type_email)
    }
    private val typePassword: TextView by lazy {
        findViewById(R.id.type_password)
    }
    private val typePassword2: TextView by lazy {
        findViewById(R.id.type_password2)
    }
    private val spEmail: Spinner by lazy {
        findViewById(R.id.sp_email)
    }

    private val btnSignUp: Button by lazy {
        findViewById(R.id.btn_signup)
    }

    // 묶음 =========================================================================================
    private val editTextArray: Array<EditText> by lazy {
        arrayOf(etName, etEmail, etEmailProvider, etPassword, etPasswordCheck)
    }
    private val validTexts: MutableMap<EditText, Boolean> by lazy {
        mutableMapOf()
    }
    private val editTextToType: Map<EditText, TextView> by lazy {
        mapOf(etName to typeName,
            etEmail to typeEmail,
            etPassword to typePassword,
            etPasswordCheck to typePassword2)
    }
    private val emptyTextErrorMessage: Map<EditText, String> by lazy {
        mapOf(etName to getString(R.string.empty_name_message),
            etEmail to getString(R.string.empty_email_message),
            etPassword to getString(R.string.empty_password_message),
            etPasswordCheck to getString(R.string.password_check_error_message))
    }
    private val invalidTextErrorMessage: Map<EditText, String> by lazy {
        mapOf(
            etName to getString(R.string.name_error_message),
            etEmail to getString(R.string.email_error_message),
            etEmailProvider to getString(R.string.email2_error_message),
            etPassword to getString(R.string.password_error_message),
            etPasswordCheck to getString(R.string.password_check_error_message)
            )
    }

    private val regexMap = mapOf(etName to "^[\\s가-힣a-zA-Z]{2,20}$",
        etEmail to "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" ,
        etEmailProvider to "[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}",
        etPassword to "/(?=(.*[0-9]))(?=.*[\\!@#\$%^&*()\\\\[\\]{}\\-_+=~`|:;\"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}/")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initView()
    }
    private fun setSpinnerServiceProvider() {
        // 스피너에 어댑터 등록한다.
        // R.array.emailList : strings.xml 내의 emailList 아이템 목록을 추가한다.
        spEmail.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf(
                getString(R.string.sign_up_email_provider_gmail),
                getString(R.string.sign_up_email_provider_kakao),
                getString(R.string.sign_up_email_provider_naver),
                getString(R.string.sign_up_email_provider_direct)
            )
        )

        // 사용자가 선택한 값을 알기 위해 Listener를 추가한다.
        spEmail.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val isVisibleProvider = position == spEmail.adapter.count - 1
                etEmailProvider.isVisible = isVisibleProvider
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }
    }

    fun initView() {
        setTextChangedListener()
        setOnFocusChangedListener()
    }
    fun setTextChangedListener() {
        editTextArray.forEach {editText ->
            editText.addTextChangedListener {
                editText.setErrorMessage()
                setButtonEnable()
            }
        }
    }

    fun setOnFocusChangedListener() {
        editTextArray.forEach {editText ->
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    editText.setErrorMessage()
                    setButtonEnable()
                }
            }
        }
    }
    fun EditText.setErrorMessage() {
        val checkEmpty = getMessageForEmptyEditText(this, emptyTextErrorMessage[this].toString())
        val checkValid = validateText(regexMap[this].toString(),this, invalidTextErrorMessage[this].toString())
        this.setVisibility(checkEmpty, checkValid)
        setButtonEnable()
    }
    fun EditText.setVisibility(checkEmpty:String, checkValid:String) {
        visibility = if (checkEmpty.isBlank() && checkValid.isBlank()) GONE else VISIBLE
    }

    fun setButtonEnable() {
        btnSignUp.isEnabled = getMessageForEmptyEditText(etName, emptyTextErrorMessage[etName].toString()).isBlank()  &&
                getMessageForEmptyEditText(etEmail, emptyTextErrorMessage[etEmail].toString()).isBlank() &&
                getMessageForEmptyEditText(etEmailProvider, emptyTextErrorMessage[etEmailProvider].toString()).isBlank() &&
                getMessageForEmptyEditText(etPassword, emptyTextErrorMessage[etPassword].toString()).isBlank() &&
                getMessageForEmptyEditText(etPasswordCheck, emptyTextErrorMessage[etPasswordCheck].toString()).isBlank() &&
                getMessageForEmptyEditText(etName, invalidTextErrorMessage[etName].toString()).isBlank() &&
                getMessageForEmptyEditText(etEmail, invalidTextErrorMessage[etEmail].toString()).isBlank() &&
                getMessageForEmptyEditText(etEmailProvider, invalidTextErrorMessage[etEmailProvider].toString()).isBlank() &&
                getMessageForEmptyEditText(etPassword, invalidTextErrorMessage[etPassword].toString()).isBlank() &&
                getMessageForEmptyEditText(etPasswordCheck, invalidTextErrorMessage[etPasswordCheck].toString()).isBlank()
    }
}