package com.example.myprofile

import android.provider.Settings.Global.getString
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import java.util.regex.Pattern

interface EditTextValidation {

    fun validateText(regex: String, editText: EditText, string: String):String {

        val text = editText.text.toString()
        val regularExpression = Regex(regex)
        return when {
            regularExpression.containsMatchIn(text)
                .not() -> return string
            else -> return ""
        }
    }
    fun getMessageForEmptyEditText(et:EditText, string:String) : String {
        if (et.text.isBlank()) {
            return string
        } else return ""
    }

}