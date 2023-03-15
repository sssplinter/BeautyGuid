package com.breaktime.signscreen.domain.pref

import com.breaktime.signscreen.data.pref.SharedPreferenceRepository

class SetUserTokenUseCase(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {
    operator fun invoke(userToken: String) {
        return sharedPreferenceRepository.setToken(userToken)
    }
}