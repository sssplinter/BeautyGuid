package com.breaktime.signscreen.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalonNews(
    val newsId: Int,
    val specialistId: Int,
    val specialistFirstName: String,
    val specialistLastName: String,
    val photoUrl: String,
    val description: String
) : Parcelable
