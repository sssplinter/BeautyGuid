package com.breaktime.signscreen.data.network.models

import com.breaktime.signscreen.data.pref.SharedPreferenceRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private var retrofit: Retrofit? = null

    // TODO for https use other ip
    private const val baseUrl = "http://10.0.2.2:8080/"

    fun getInstance(sharedPreferenceRepository: SharedPreferenceRepository): Retrofit {
        if (retrofit == null) {
            val client = OkHttpClient.Builder()
                .addInterceptor(ServiceInterceptor(sharedPreferenceRepository))
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
        return retrofit ?: throw IllegalStateException()
    }
}