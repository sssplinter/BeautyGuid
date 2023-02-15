package com.breaktime.signscreen.screen.authorization.login

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.utils.isValidEmail
import com.breaktime.signscreen.utils.isValidPassword

class LoginViewModel : ViewModel() {
    var login by mutableStateOf("")
    var isValidLogin by mutableStateOf(true)

    var password by mutableStateOf("")
    var isValidPassword by mutableStateOf(true)

    fun onLoginValueChange(value: String) {
        login = value
        isValidLogin = login.isNotBlank()
    }

    fun onPasswordValueChange(value: String) {
        password = value
        isValidPassword = password.isNotBlank()
    }

    fun onLoginClick() {
        isValidPassword = isValidPassword(password)
        isValidLogin = isValidEmail(login) && login.isNotBlank()
        if( isValidLogin && isValidPassword){
            // TODO request
        }
    }
}