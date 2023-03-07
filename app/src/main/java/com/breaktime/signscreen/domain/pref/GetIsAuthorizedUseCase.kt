package com.breaktime.signscreen.domain.pref

import com.breaktime.signscreen.data.pref.SharedPreferenceRepository

class GetIsAuthorizedUseCase(private val sharedPreferenceRepository: SharedPreferenceRepository) {
    operator fun invoke(): Boolean {
        return sharedPreferenceRepository.getIsAuthorized()
    }
}