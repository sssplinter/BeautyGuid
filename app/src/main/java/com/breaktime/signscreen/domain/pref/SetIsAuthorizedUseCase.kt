package com.breaktime.signscreen.domain.pref

import com.breaktime.signscreen.data.pref.SharedPreferenceRepository

class SetIsAuthorizedUseCase(private val sharedPreferenceRepository: SharedPreferenceRepository) {
    operator fun invoke(isAuthorized: Boolean) {
        return sharedPreferenceRepository.setIsAuthorized(isAuthorized)
    }
}