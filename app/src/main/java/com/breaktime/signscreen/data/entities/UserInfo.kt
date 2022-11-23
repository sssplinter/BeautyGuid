package com.breaktime.signscreen.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(val userId: String?, val userRole: String?, val userName: String) : Parcelable