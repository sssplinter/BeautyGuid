package com.breaktime.signscreen.screen.onboarding

import com.breaktime.signscreen.R

data class OnBoardingItem(
    val title: Int,
    val text: Int,
    val Image: Int,
) {
    companion object {

        fun get() = listOf(
            OnBoardingItem(R.string.title1, R.string.text1, R.drawable.ab1_inversions),
            OnBoardingItem(R.string.title2, R.string.text1, R.drawable.ab1_inversions),
            OnBoardingItem(R.string.title3, R.string.text1, R.drawable.ab1_inversions)
        )
    }
}
