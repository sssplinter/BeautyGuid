package com.breaktime.signscreen.data.pref

import android.content.Context

private const val USER_PREFERENCES_NAME = "user_preferences"

private const val IS_FIRST_ENTER_KEY = "is_first_enter"
private const val IS_AUTHORIZED_KEY = "is_authorized"
private const val TOKEN_KEY = "token"
private const val USER_ID_KEY = "userId"

class SharedPreferenceRepository(context: Context) {

    private val sharedPreferences =
        context.applicationContext.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getIsFirstEnter(): Boolean = sharedPreferences.getBoolean(IS_FIRST_ENTER_KEY, true)

    fun setIsFirstEnter(isFirstEnter: Boolean) {
        sharedPreferences.edit()
            .putBoolean(IS_FIRST_ENTER_KEY, isFirstEnter)
            .apply()
    }

    fun getIsAuthorized(): Boolean = sharedPreferences.getBoolean(IS_AUTHORIZED_KEY, false)

    fun setIsAuthorized(isAuthorized: Boolean) {
        sharedPreferences.edit()
            .putBoolean(IS_AUTHORIZED_KEY, isAuthorized)
            .apply()
    }

    fun getToken(): String? =
        sharedPreferences.getString(TOKEN_KEY, null)


    fun setToken(token: String) {
        sharedPreferences.edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    fun getUserId(): String? =
        sharedPreferences.getString(USER_ID_KEY, null)


    fun setUSerId(userId: String) {
        sharedPreferences.edit()
            .putString(USER_ID_KEY, userId)
            .apply()
    }
}