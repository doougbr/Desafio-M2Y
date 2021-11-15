package com.example.desafiom2yandroid.repository

import com.example.desafiom2yandroid.model.network.ApiService
import com.example.desafiom2yandroid.model.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val appKey = "be662d38fa2d418aa23e8e1c9fdf3e04"

class MoviesRepository {

    companion object {
        const val TAG = "MoviesRepository"
    }

//    private val retrofitClient = MovieApi.retrofitService

    private val retrofitClient = RetrofitInstance()
        .createService(ApiService::class.java)

    suspend fun getMovieDetails() = withContext(Dispatchers.IO) {
        retrofitClient.getMovieDetails(appKey)
    }

    suspend fun getSimilarMovies() = withContext(Dispatchers.IO) {
        retrofitClient.getSimilarMovies(appKey)
    }
}