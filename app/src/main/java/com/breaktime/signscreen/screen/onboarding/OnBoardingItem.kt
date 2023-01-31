package com.breaktime.signscreen.screen.onboarding

import com.breaktime.signscreen.R

data class OnBoardingItem(
    val title: Int,
    val text: Int,
    val image: Int,
) {
    companion object {

        fun get() = listOf(
            OnBoardingItem(R.string.title1, R.string.text1, R.drawable.onboarding_mask),
            OnBoardingItem(R.string.title1, R.string.text1, R.drawable.onboarding_make_up),
            OnBoardingItem(R.string.title1, R.string.text1, R.drawable.onboarding_botox),
            OnBoardingItem(R.string.title1, R.string.text1, R.drawable.onboarding_hair_cut),
            OnBoardingItem(R.string.title1, R.string.text1, R.drawable.onboarding_lazer)
        )
    }
}
