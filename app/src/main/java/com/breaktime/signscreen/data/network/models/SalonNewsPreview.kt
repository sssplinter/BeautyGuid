package com.breaktime.signscreen.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SalonNewsPreview(
    val newsId: Int,
    val photoUrl: String
) : Parcelable