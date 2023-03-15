package com.breaktime.signscreen.data.network.models

import com.breaktime.signscreen.data.pref.SharedPreferenceRepository
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor(private val sharedPreferenceRepository: SharedPreferenceRepository) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            val token = sharedPreferenceRepository.getToken()

            if (!token.isNullOrEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }

        return chain.proceed(request)
    }
}