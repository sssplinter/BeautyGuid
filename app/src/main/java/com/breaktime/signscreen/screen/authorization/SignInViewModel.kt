package com.breaktime.signscreen.screen.authorization

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.utils.isValidEmail
import com.breaktime.signscreen.utils.isValidPassword

class SignInViewModel : ViewModel() {
    var email by mutableStateOf("")
    var isValidEmail by mutableStateOf(true)

    var password by mutableStateOf("")
    var isValidPassword by mutableStateOf(true)

    fun onEmailValueChange(value: String) {
        email = value
        isValidEmail = email.isNotBlank()
    }

    fun onPasswordValueChange(value: String) {
        password = value
        isValidPassword = password.isNotBlank()
    }

    fun onSignInClick(): Boolean {
        isValidPassword = isValidPassword(password)
        isValidEmail = isValidEmail(email) && email.isNotBlank()
        return isValidEmail && isValidPassword
    }
}

@Stable
interface InputFieldState {
    var value: String
    val isValid: Boolean
}