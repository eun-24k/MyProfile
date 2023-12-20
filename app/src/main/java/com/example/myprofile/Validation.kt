package com.example.myprofile

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import java.util.regex.Pattern

interface Validation {

    fun validate(regex: String, editText: EditText):Boolean {
        return Pattern.compile(regex).matcher(editText.toString()).find()
    }

    fun check(idEditText: EditText, signInBtn: Button, pwdEditText: EditText) {
        idEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                signInBtn.isEnabled = idEditText.text.isNotEmpty() && pwdEditText.text.isNotEmpty()
            }
        })
    }

}