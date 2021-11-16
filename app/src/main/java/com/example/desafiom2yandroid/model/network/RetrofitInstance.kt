package com.example.desafiom2yandroid.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

class RetrofitInstance {

    private lateinit var retrofit: Retrofit

    private fun getRetrofitInstance(): Retrofit {

        if (!::retrofit.isInitialized) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    fun <T> createService(serviceClass: Class<T>): T {
        return getRetrofitInstance().create(serviceClass)
    }
}