package com.breaktime.signscreen.utils

import android.util.Patterns

fun isValidEmail(email: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches()

// TODO implement more complex logic
fun isValidPassword(password: String): Boolean =
    password.length > 8