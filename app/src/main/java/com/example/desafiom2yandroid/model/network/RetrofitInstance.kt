package com.example.desafiom2yandroid.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

class RetrofitInstance {
/*val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

object MovieApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java)}
}*/

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

    // essa funcao permite que seja criado vários services utilizando a mesma funcao
    // de tipo genérico, que no caso é o <T>, ou seja, se eu tiver vários services
    // diferentes (GET, POST, etc), eu posso usar a mesma funcao para todos
    fun <T> createService(serviceClass: Class<T>): T {
        return getRetrofitInstance().create(serviceClass)
    }
}