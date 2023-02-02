package com.breaktime.signscreen.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.breaktime.signscreen.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val Typography.salonH1
    @Composable
    get() = TextStyle(
        fontFamily = fontFamilyKulim,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        letterSpacing = (1.15).sp
    )

val Typography.salonH2
    @Composable
    get() = TextStyle(
        fontFamily = fontFamilyKulim,
        fontSize = 15.sp,
        letterSpacing = (1.15).sp
    )

val Typography.salonH3
    @Composable
    get() = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )

val Typography.salonH5
    @Composable
    get() = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    )

val Typography.salonH6
    @Composable
    get() = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.25.sp
    )

val Typography.salonBody1
    @Composable
    get()  = TextStyle(
        fontFamily = fontFamilyLato,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )

val Typography.address
    @Composable
    get()  = TextStyle(
        fontWeight = FontWeight.W400,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    )

val Typography.button
    @Composable
    get()  = TextStyle(
        fontFamily = fontFamilyLato,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (1.15).sp
    )

val Typography.salonCaption
    @Composable
    get()  = TextStyle(
        fontFamily = fontFamilyKulim,
        fontSize = 14.sp,
        letterSpacing = (1.15).sp
    )

private val fontFamilyKulim = FontFamily(
    listOf(
        Font(
            resId = R.font.kulim_park_regular
        ),
        Font(
            resId = R.font.kulim_park_light,
            weight = FontWeight.Light
        )
    )
)

private val fontFamilyLato = FontFamily(
    listOf(
        Font(
            resId = R.font.lato_regular
        ),
        Font(
            resId = R.font.lato_bold,
            weight = FontWeight.Bold
        )
    )
)