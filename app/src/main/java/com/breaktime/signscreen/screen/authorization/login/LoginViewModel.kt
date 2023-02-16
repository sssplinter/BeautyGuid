package com.breaktime.signscreen.screen.authorization.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breaktime.signscreen.domain.authorization.LoginUseCase
import com.breaktime.signscreen.utils.isValidEmail
import com.breaktime.signscreen.utils.isValidPassword
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    // TODO replace by injection
    private val loginUseCase: LoginUseCase = LoginUseCase()

    var login by mutableStateOf("")
//    var isValidLogin by mutableStateOf(true)

    var password by mutableStateOf("")
//    var isValidPassword by mutableStateOf(true)

    var token by mutableStateOf("")

    var errorMessage by mutableStateOf("")

    fun onLoginValueChange(value: String) {
        login = value
//        isValidLogin = login.isNotBlank()
    }

    fun onPasswordValueChange(value: String) {
        password = value
//        isValidPassword = password.isNotBlank()
    }

    fun onLoginClick() {
        when {
            !isValidPassword(password) -> {
                errorMessage = "Password must contain at least 8 characters"
            }
            !isValidEmail(login) -> {
                errorMessage = "Incorrect email"
            }
            else -> {
                viewModelScope.launch {
                    token = loginUseCase(login, password)?.token ?: ""
                }
            }
        }
    }
}