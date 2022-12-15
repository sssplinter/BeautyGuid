package com.breaktime.signscreen.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Patterns
import androidx.annotation.AnyRes


fun isValidEmail(email: String): Boolean =
    Patterns.EMAIL_ADDRESS.matcher(email).matches()

// TODO implement more complex logic
fun isValidPassword(password: String): Boolean =
    password.length > 8

fun getUriToDrawable(
    context: Context,
    @AnyRes drawableId: Int
): Uri {
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + context.resources.getResourcePackageName(drawableId)
                + '/' + context.resources.getResourceTypeName(drawableId)
                + '/' + context.resources.getResourceEntryName(drawableId)
    )
}