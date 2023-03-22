package com.breaktime.signscreen.data.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRequestInfo(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("mobileNumber")
    val mobileNumber: String,
    @SerializedName("email")
    val email: String
) : Parcelable
