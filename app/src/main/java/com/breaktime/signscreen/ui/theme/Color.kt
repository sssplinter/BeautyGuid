package com.breaktime.signscreen.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Pink = Color(0xFFEB929B)
val Pink2 = Color(0xEBEB929B)

val HintGrey = Color(0xFFa8a8a8)
val HintWhite = Color(0xFFF3ECEC)
val HintLightGrey = Color(0xFFDDD6D6)

val BorderLight = Color(0xFFFFFFFF)
val BorderDark = Color(0xFF0C0B0B)

val NotPrimaryTextLight = Color(0xFFFFFFFF)
val NotPrimaryTextDark = Color(0xFF7E7979)

val LightGray = Color(0xFFDBDBDB)


val Colors.StarYellow
    @Composable
    get() = Color(0xFFffcc03)

val Colors.hintColor
    @Composable
    get() = if (isLight) HintGrey else HintWhite

val Colors.unreachable
    @Composable
    get() = if (isLight) LightGray else HintWhite

val Colors.NoRatingColor
    @Composable
    get() = if (isLight) HintGrey else HintLightGrey

val Colors.BorderColor
    @Composable
    get() = if (isLight) BorderDark else BorderLight

val Colors.NotPrimaryText
    @Composable
    get() = if (isLight) NotPrimaryTextDark else NotPrimaryTextLight