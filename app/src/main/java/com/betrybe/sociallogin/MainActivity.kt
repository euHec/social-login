package com.betrybe.sociallogin

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private val email_input by lazy { findViewById<TextInputLayout>(R.id.email_text_input_layout)}
    private val password_input by lazy { findViewById<TextInputLayout>(R.id.password_text_input_layout) }
    private val button by lazy { findViewById<Button>(R.id.login_button) }
    private fun validarEmail(email: Editable): Boolean {
        val regex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z]+\\.[A-Za-z]{2,}")
        return regex.matches(email)
    }
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = email_input.editText!!
        val password = password_input.editText!!

        email.addTextChangedListener {
            button.isEnabled = email.text.isNotEmpty() && password.text.isNotEmpty()
        }

        password.addTextChangedListener {
            button.isEnabled = email.text.isNotEmpty() && password.text.isNotEmpty()
        }

        button.setOnClickListener {
            val isTrue = validarEmail(email.text)
            val isValidPassword = password.text.length <= 4
            if (!isTrue || isValidPassword) {
                if (!isTrue) email_input.error = getString(R.string.email_warning)
                if (isValidPassword) password_input.error = getString(R.string.password_warning)
                return@setOnClickListener
            }
            email_input.error = null
            password_input.error = null
            if (isTrue && !isValidPassword) {
                Snackbar.make(findViewById(android.R.id.content), R.string.login_succeeded, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
