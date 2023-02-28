package com.breaktime.signscreen.screen.photoPreview

import androidx.lifecycle.ViewModel
import com.breaktime.signscreen.R
import com.breaktime.signscreen.data.entities.SalonPhotoInfo

class SalonPhotosViewModel : ViewModel() {
    // TODO replace by real data
    private val _salonPhotosInfo = getTestdata()

    // TODO investigate how to get this data
    val salonName = "Frau Marta"
    val salonPhoto = R.drawable.fc3_stress_and_anxiety

    val salonPhotosInfo: List<SalonPhotoInfo>
        get() = _salonPhotosInfo
}

fun getTestdata() = listOf<SalonPhotoInfo>(
    SalonPhotoInfo(
        0,
        R.drawable.im_nails,
        "Kristina Sementsova",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        1,
        R.drawable.im_nails2,
        "Anna Smith",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        2,
        R.drawable.im_nails3,
        "Kristina Sementsova",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        3,
        R.drawable.im_nails,
        "Kristina Sementsova",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        4,
        R.drawable.im_nails2,
        "Anna Smith",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        5,
        R.drawable.im_nails3,
        "Kristina Sementsova",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        6,
        R.drawable.im_nails,
        "Kristina Sementsova",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        7,
        R.drawable.im_nails2,
        "Anna Smith",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
    SalonPhotoInfo(
        8,
        R.drawable.im_nails3,
        "Kristina Sementsova",
        "2",
        "Classic and French manicure. Removal, long-term coating, gel polish, manicure, nail extension."
    ),
)