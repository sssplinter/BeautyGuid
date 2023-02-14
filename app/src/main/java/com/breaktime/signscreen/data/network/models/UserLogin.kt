package com.breaktime.signscreen.data.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLoginRequestInfo
    (
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
) : Parcelable